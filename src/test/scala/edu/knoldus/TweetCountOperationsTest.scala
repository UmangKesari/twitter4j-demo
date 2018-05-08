package edu.knoldus

import twitter4j.TwitterException
import org.scalatest.AsyncFunSuite

class TweetCountOperationsTest extends AsyncFunSuite {
  val tweetCountOperations = new TweetCountOperations

  test("Testing Count for Hashtag Count for existing hashtag") {
    val resultTweets = tweetCountOperations.tweetHashtagCount("SLvIND")
    resultTweets.map(list => assert(list.nonEmpty))
  }

  test("Testing Count for Hashtag Count for non-existing hashtag ") {
    recoverToSucceededIf[TwitterException] {
      tweetCountOperations.tweetHashtagCount("")
    }

  }

  test("Testing average tweet for the valid user") {
    tweetCountOperations.averageTweetPerday("virendersehwag").map(count => assert(count > 0))
  }

  test("Testing average tweet for the invalid user") {
    recoverToSucceededIf[TwitterException] {
      tweetCountOperations.numberOfTweets("448658694")
    }
  }

  test("Testing Tweet Count for valid user") {
    tweetCountOperations.numberOfTweets("virendersehwag").map(count => assert(count > 0))
  }

  test("Tesing tweet count for invalid user ") {
    recoverToSucceededIf[TwitterException] {
      tweetCountOperations.numberOfTweets("4327583432")
    }
  }

  test("Testing average ReTweet for valid user") {
    tweetCountOperations.averageReTweets("virendersehwag").map(count => assert(count > 0))
  }

  test("Tesing average Retweet for invalid user ") {
    recoverToSucceededIf[TwitterException] {
      tweetCountOperations.numberOfTweets("4327583432")
    }
  }

  test("Testing average likes for the tweet for valid user") {
    tweetCountOperations.averageReTweets("virendersehwag").map(count => assert(count > 0))
  }

  test("Tesing average likes for the tweet for invalid user ") {
    recoverToSucceededIf[TwitterException] {
      tweetCountOperations.numberOfTweets("4327583432")
    }
  }


}
