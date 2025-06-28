package pt.personal.cats.service

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import pt.personal.cats.model.Vote

class CatsServiceTest {
 private val catsService = CatsService()

 @Test
 fun `voteCat should register a vote and return confirmation message`() {
  val vote = Vote(id = "cat123", score = 4)
  val result = catsService.voteCat(vote).message
  assertEquals("Estou a ronronar de contente! Gosto do teu voto! \uD83D\uDE3B", result)
  val votes = catsService.getAllVotes()
  assertTrue(votes.any { it.id == "cat123" && it.score == 4 })
 }

 @Test
 fun `getAllVotes should return all votes`() {
  catsService.voteCat(Vote("catA", 5))
  catsService.voteCat(Vote("catB", 3))
  val votes = catsService.getAllVotes()
  assertEquals(2, votes.size)
  assertTrue(votes.any { it.id == "catA" && it.score == 5 })
  assertTrue(votes.any { it.id == "catB" && it.score == 3 })
 }

 @Test
 fun `getRandomCat should return a Cat with non-empty url and id`() {
  val cat = catsService.getRandomCat()
  assertNotNull(cat)
  assertTrue(cat.imgUrl.isNotEmpty())
  assertTrue(cat.id.isNotEmpty())
 }
}