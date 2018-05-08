package edu.knoldus

//import java.util.stream.Collectors

import java.util.Calendar
import twitter4j._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.collection.JavaConverters._
//import twitter4j.conf.ConfigurationBuilder


class TweetCountOperations {


  /*val cf : ConfigurationBuilder = ConfigurationBuilder;
  cf.setDebugEnabled(true)
    .setOAuthConsumerKey("D6ewek8eWnNSYZQEmnx6CM4P0")
    .setOAuthConsumerSecret("xrpjoijP6W5PiMXUrUzLff59KbBDuq5pFbIlfHLuuTJWBGeEtM")
    .setOAuthAccessToken("359913805-83M0CXX5XF3NsaJKU5W1GgTe6Uqs8N5rIkh5CrIe")
    .setOAuthAccessTokenSecret("HrCWRroMIrrmEtq4rxDELnmaw6RCUhkkamM6ouvbkgBEy")*/


  val tweet: Twitter = TwitterFactory.getSingleton

  def tweetHashtagCount(hashtag: String): Future[List[String]] = {
    Future {
      val query: Query = new Query(hashtag)
      val result = tweet.search(query)

      val resultList = result.getTweets.asScala.toList
      resultList.map(status => "@" + status.getUser.getScreenName + ": " + status.getText)
    }
  }

  def averageTweetPerday(username: String): Future[Double] = {
      for {tweetCount <- numberOfTweets(username)
           totalDays <- getNumberOfDaysOfTwitterUser(username)
      } yield tweetCount/totalDays.toDouble

  }

  def numberOfTweets(username: String): Future[Long] = {
    Future {
      tweet.showUser(username).getStatusesCount
    }
  }

  private def getNumberOfDaysOfTwitterUser(username : String) : Future[Long]={
    Future{
      val startDate = tweet.showUser(username).getCreatedAt.getTime
      val currentDate = Calendar.getInstance().getTimeInMillis

      (currentDate - startDate) / (24 * 60 * 60 * 1000)
    }
  }
  def averageReTweets(username: String): Future[Double] = {
    Future {
      val retweets = tweet.timelines().getUserTimeline(username).asScala.toList

      retweets.map(_.getRetweetCount).sum / retweets.length.toDouble
    }
  }

  def averageLikes(username: String): Future[Double] = {
    Future {
      val likes = tweet.timelines().getUserTimeline(username).asScala.toList
      likes.map(_.getFavoriteCount).sum / likes.length.toDouble
    }
  }

}



object twitter extends App{
  val tweetObj = new TweetCountOperations
  val resultTweets = tweetObj.tweetHashtagCount("ManU")
  Thread.sleep(1000)

  resultTweets.map(_.map(res => println(res + "\n")))

  val result = tweetObj.numberOfTweets("virender sehwag")
  Thread.sleep(5000)

  result.map(println(_))

}
