package pt.personal.cats.service

import pt.personal.cats.model.Cat
import pt.personal.cats.model.Vote
import pt.personal.cats.model.VoteResponse
import java.util.Collections
import java.util.UUID

class CatsService {

    private val catVotes = Collections.synchronizedMap(mutableMapOf<String, Int>())

    fun voteCat(vote: Vote): VoteResponse {
        require(vote.score in 1..5) { "Score deve estar entre 1 e 5" }
        catVotes[vote.id] = vote.score

        val message = when (vote.score) {
            1 -> "Miau... Acho que hoje não estou muito no meu melhor, né? 😿"
            2 -> "Epá, podia ser melhor... mas estou a tentar, vá lá! 🙀"
            3 -> "Está aceitável, não é mau. Um gato razoável! 😼"
            4 -> "Estou a ronronar de contente! Gosto do teu voto! 😻"
            5 -> "Uau! Nota máxima? És o meu humano preferido! 🐾😽"
            else -> "Miau? Não percebi bem essa nota."
        }

        return VoteResponse(message)
    }

    fun getAllVotes(): List<Vote> {
        return catVotes.entries.map { entry -> Vote(entry.key, entry.value) };
    }

    fun getRandomCat(): Cat {
        val uniqueId = UUID.randomUUID().toString()
        return Cat("https://cataas.com/cat?unique=${uniqueId}", uniqueId)
    }
}