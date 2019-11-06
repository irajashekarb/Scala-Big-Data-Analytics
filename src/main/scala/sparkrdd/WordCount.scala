package sparkrdd

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]):Unit = {
    val conf = new SparkConf().setAppName("Spark Count App").setMaster("local[*]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("WARN")
    val inputFileName = "C:\\Users\\iraja\\Documents\\Github\\Scala Spark Practice\\Scala-Big-Data-Analytics\\InputFile.txt"

    sc.textFile(inputFileName)
      .flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)
      .saveAsTextFile("out.txt")
  }
}
