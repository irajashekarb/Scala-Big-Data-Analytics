package TwitterSentimentAnalysis

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

object SparkPopularHashTags {
  val conf = new SparkConf().setMaster("local[2]").setAppName("Spark Streaming - Popular Hashtags")
  val sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {
    sc.setLogLevel("WARN")

    val Array(consumerKey, consumerSecret, accessToken, accessTokenSecret) = args.take(4)
    val filters = args.takeRight(args.length - 4)

    System.setProperty("twitter4j.oauth.consumerKey", consumerKey)
    System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret)
    System.setProperty("twitter4j.oauth.accessToken", accessToken)
    System.setProperty("twitter4j.oauth.accessTokenSecret", accessTokenSecret)

    val ssc = new StreamingContext(sc, Seconds(5))

    val stream = TwitterUtils.createStream
  }
}
