import React, { useEffect, useState } from "react";

function App() {
  const [cat, setCat] = React.useState(null);
  const [message, setMessage] = React.useState("");
  const [votes, setVotes] = React.useState([]);

  React.useEffect(() => {
    fetchCat();
  }, []);

  async function fetchCat() {
    const res = await fetch("/cat");
    const cat = await res.json();
    setCat(cat);
    setMessage("");
  }

  async function voteCat(score) {
    const res = await fetch("/vote", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ id: cat.id, score }),
    });
    const data = await res.json();
    setMessage(data.message);
    setTimeout(() => {
      fetchCat();
      setMessage("");
    }, 5000);
  }

  async function fetchVotes() {
    const res = await fetch("/votes");
    const votes = await res.json();
    setVotes(votes);
  }

  return (
    <div style={styles.container}>
      <h1 style={styles.title}>Vote no Gato 🐱</h1>

      {cat && (
        <div style={styles.catContainer}>
          <img
            src={cat.imgUrl}
            alt="Gato fofo"
            style={styles.catImage}
          />
        </div>
      )}

      <div style={styles.buttonsContainer}>
        {[1, 2, 3, 4, 5].map((score) => (
          <button
            key={score}
            onClick={() => voteCat(score)}
            style={styles.voteButton}
          >
            {score}
          </button>
        ))}
      </div>

      {message && <p style={styles.message}>{message}</p>}

      <button onClick={fetchVotes} style={styles.showVotesButton}>
        Mostrar votos
      </button>

      <ul style={styles.votesList}>
        {votes.map((v) => (
          <li key={v.id} style={styles.voteItem}>
            Gato <strong>{v.id.substring(0, 5)}</strong> - Nota:{" "}
            <strong>{v.score}</strong>
          </li>
        ))}
      </ul>
    </div>
  );
}

const styles = {
  container: {
    maxWidth: 480,
    margin: "20px auto",
    padding: 20,
    fontFamily: "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif",
    backgroundColor: "#fff",
    boxShadow: "0 0 15px rgba(0,0,0,0.1)",
    borderRadius: 12,
    textAlign: "center",
  },
  title: {
    marginBottom: 24,
    color: "#333",
  },
  catContainer: {
    marginBottom: 20,
    borderRadius: 16,
    overflow: "hidden",
    boxShadow: "0 4px 12px rgba(0,0,0,0.1)",
  },
  catImage: {
    width: "100%",
    height: "auto",
    display: "block",
  },
  buttonsContainer: {
    marginBottom: 16,
    display: "flex",
    justifyContent: "center",
    gap: 12,
  },
  voteButton: {
    backgroundColor: "#4caf50",
    border: "none",
    color: "white",
    padding: "12px 18px",
    borderRadius: 8,
    fontSize: 18,
    cursor: "pointer",
    transition: "background-color 0.3s",
  },
  message: {
    margin: "12px 0",
    fontSize: 16,
    color: "#444",
  },
  showVotesButton: {
    marginTop: 12,
    backgroundColor: "#2196f3",
    color: "white",
    border: "none",
    padding: "10px 20px",
    borderRadius: 8,
    fontSize: 16,
    cursor: "pointer",
  },
  votesList: {
    listStyleType: "none",
    paddingLeft: 0,
    marginTop: 16,
    maxHeight: 200,
    overflowY: "auto",
    textAlign: "left",
    borderTop: "1px solid #eee",
  },
  voteItem: {
    padding: "8px 0",
    borderBottom: "1px solid #eee",
    fontSize: 14,
  },
};

export default App;
