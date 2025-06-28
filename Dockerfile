# Step 1 - Build Frontend
FROM node:18-alpine AS frontend-builder
WORKDIR /app/react-app
COPY react-app/package*.json ./
RUN npm install
COPY react-app/ ./
RUN npm run build

# Step 2 - Build Backend
FROM eclipse-temurin:21-jdk AS backend-builder
WORKDIR /app
COPY . .
COPY --from=frontend-builder /app/react-app/build/ ./src/main/resources/static/
WORKDIR /app
RUN chmod +x ./gradlew
RUN ./gradlew build --no-daemon

# Step 3 - Run app
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=backend-builder /app/build/libs/cats-0.1.0.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
