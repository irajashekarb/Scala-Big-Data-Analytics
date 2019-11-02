package sparkrdd

import org.apache.spark.{SparkConf, SparkContext}

object RDDTempData {
  def main(args: Array[String]): Unit = {
    //Creating configuration for our spark context
    val conf = new SparkConf().setAppName("Temp Data").setMaster("local[*]")
    val sc = new SparkContext(conf)

    //Loading lines using textFile method
    val lines = sc.textFile("MN212142_9392.csv")

    //printing first five lines from our file
    lines.take(5) foreach(println)
  }
}
