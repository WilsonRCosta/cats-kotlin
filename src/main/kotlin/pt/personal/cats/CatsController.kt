package pt.personal.cats

import org.springframework.web.bind.annotation.*
import pt.personal.cats.model.Vote
import pt.personal.cats.service.CatsService

@RestController
@CrossOrigin(origins = ["*"])
class CatsController {

    val catsService = CatsService();

    @GetMapping("/cat")
    fun getRandomCat() = catsService.getRandomCat()

    @PostMapping("/vote")
    fun voteOnCat(@RequestBody vote: Vote) = catsService.voteCat(vote)

    @GetMapping("/votes")
    fun getAllVotes() = catsService.getAllVotes()
}