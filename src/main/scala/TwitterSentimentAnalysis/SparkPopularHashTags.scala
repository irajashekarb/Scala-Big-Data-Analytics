package TwitterSentimentAnalysis

import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
object SparkPopularHashTags {
  val conf = new SparkConf().setMaster("local[2]").setAppName("Spark Streaming - Popular Hashtags")
  val sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {
    sc.setLogLevel("WARN")

    val Array(dJTvoQO0jLCHKPKGKWun91Tk0 , gQ2910NqZZtoFrtKPioQba9iXZNaEOqPN8v7gCQivalUDsMvq4, o3Lrwu8GvPa5GG5yPLBt6uXu7aBLbqa6pt7Brfs, qTZRuYY2HphAkYZyaZUn8kU79GVsR0JC1aFCTWBlI4WCg) = args.take(4)
    val filters = args.takeRight(args.length - 4)

    System.setProperty("twitter4j.oauth.consumerKey", dJTvoQO0jLCHKPKGKWun91Tk0)
    System.setProperty("twitter4j.oauth.consumerSecret", gQ2910NqZZtoFrtKPioQba9iXZNaEOqPN8v7gCQivalUDsMvq4)
    System.setProperty("twitter4j.oauth.accessToken", o3Lrwu8GvPa5GG5yPLBt6uXu7aBLbqa6pt7Brfs)
    System.setProperty("twitter4j.oauth.accessTokenSecret", qTZRuYY2HphAkYZyaZUn8kU79GVsR0JC1aFCTWBlI4WCg)

    val ssc = new StreamingContext(sc, Seconds(5))

    val stream = TwitterUtils.createStream(ssc, None, filters)

    val hashTags = stream.flatMap(status => status.getText.split(" ").filter(_.startsWith("#")))

    val topCounts60 = hashTags.map((_, 1)).reduceByKeyAndWindow(_ + _, Seconds(60)).map { case (topic, count) => (count, topic) }.transform(_.sortByKey(false))

    val topCounts10 = hashTags.map((_, 1)).reduceByKeyAndWindow(_ + _, Seconds(10))
      .map { case (topic, count) => (count, topic) }
      .transform(_.sortByKey(false))

    stream.print()

    topCounts60.foreachRDD(rdd => {
      val topList = rdd.take(10)
      println("\nPopular topics in last 60 seconds (%s total):".format(rdd.count()))
      topList.foreach { case (count, tag) => println("%s (%s tweets)".format(tag, count)) }
    })
    topCounts10.foreachRDD(rdd => {
      val topList = rdd.take(10)
      println("\nPopular topics in last 10 seconds (%s total):".format(rdd.count()))
      topList.foreach { case (count, tag) => println("%s (%s tweets)".format(tag, count)) }
    })

    ssc.start()
    ssc.awaitTermination()
  }
}
