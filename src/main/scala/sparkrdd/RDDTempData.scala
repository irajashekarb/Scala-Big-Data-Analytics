package sparkrdd

import org.apache.spark.{SparkConf, SparkContext}
import standartscala.TempData

object RDDTempData {
  def main(args : Array[String]): Unit = {
    //Creating configuration for our spark context
    val conf = new SparkConf().setAppName("Temp Data").setMaster("local[*]")
    val sc = new SparkContext(conf)

    //Loading lines using textFile method
    val lines = sc.textFile("MN212142_9392.csv").filter(!_.contains("Day"))

    val data = lines.flatMap
    {
      line =>
      val p = line.split(",")
      if(p(7)=="." || p(8)=="." || p(9)==".") Seq.empty
      else
        Seq(TempData(p(0).toInt, p(1).toInt, p(2).toInt, p(4).toInt,
          TempData.toDoubleOrNeg(p(5)), TempData.toDoubleOrNeg(p(6)), p(7).toDouble, p(8).toDouble,
          p(9).toDouble))
    }

    println(data.max()(Ordering.by(_.tmax)))

    println(data.reduce((td1, td2) => if(td1.tmax == td2.tmax) td1 else td2))
  }
}
