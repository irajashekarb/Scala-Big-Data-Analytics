package sparkrdd

import org.apache.spark.{SparkConf, SparkContext}

object RDDTempData {
  def main(args: Array[String]): Unit = {
    //Creating configuration for our spark context
    val conf = new SparkConf().setAppName("Temp Data").setMaster("local[*]")
    //Creating spark context with the configuration above
    val sc = new SparkContext(conf)

    //Reading the lines from the file using textFile method on sc
    val lines = sc.textFile("MN212142_9392.csv")

    //Taking first five lines and printing each line
    lines.take(5) foreach(println)

  }
}
