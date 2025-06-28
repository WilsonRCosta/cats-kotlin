package pt.personal.cats.service

import pt.personal.cats.model.Cat
import pt.personal.cats.model.Vote
import java.util.*

class CatsService {

    private val catVotes = Collections.synchronizedMap(mutableMapOf<String, Int>())

    fun voteCat(vote: Vote): String {
        require(vote.score in 1..5) { "Score deve estar entre 1 e 5" }
        catVotes[vote.id] = vote.score
        return "Voto registrado para o gato %s com nota %s!".format(vote.id, vote.score)
    }

    fun getAllVotes(): List<Vote> {
        return catVotes.entries.map { entry -> Vote(entry.key, entry.value) };
    }

    fun getRandomCat(): Cat {
        return Cat("https://cataas.com/cat", UUID.randomUUID().toString())
    }
}