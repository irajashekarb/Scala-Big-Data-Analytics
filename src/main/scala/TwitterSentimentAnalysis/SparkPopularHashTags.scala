package TwitterSentimentAnalysis

import org.apache.spark.{SparkConf, SparkContext}

object SparkPopularHashTags {
  val conf = new SparkConf().setMaster("local[2]").setAppName("Spark Streaming - Popular Hashtags")
  val sc = new SparkContext(conf)

  def main(args: Array[String]): Unit = {
    sc.setLogLevel("WARN")

    val Array(consumerKey, consumerSecret, accessToken, accessTokenSecret) = args.take(4)
    val filters = args.takeRight(args.length - 4)

    
  }
}
