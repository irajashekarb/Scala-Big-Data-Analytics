package sparkrdd

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]):Unit = {
    val conf = new SparkConf().setAppName("Spark Count App").setMaster("Local[*]")
    val sc = new SparkContext(conf)

    val inputFileName = "InputFile.txt"
    val outputFileName = "/out.txt"

    sc.textFile()
  }
}
