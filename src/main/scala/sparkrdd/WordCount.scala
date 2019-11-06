package sparkrdd

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]):Unit = {
    val conf = new SparkConf().setAppName("Spark Count App").setMaster("local[*]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")
    val inputFileName = "InputFile.txt"
    val outputFileName = "Scala-Big-Data-Analytics/out.txt"

    sc.textFile(inputFileName)
      .flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)
      .saveAsTextFile(outputFileName)
  }
}
