package pt.personal.cats

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import pt.personal.cats.model.Vote
import pt.personal.cats.service.CatsService

@RestController
@CrossOrigin(origins = ["*"])
class CatsController {

    val catsService = CatsService()

    @GetMapping("/cat")
    fun getRandomCat() = catsService.getRandomCat()

    @PostMapping("/vote")
    fun voteOnCat(@RequestBody vote: Vote) = catsService.voteCat(vote)

    @GetMapping("/votes")
    fun getAllVotes() = catsService.getAllVotes()
}