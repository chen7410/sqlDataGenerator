import scala.io.Source
import collection.immutable.Set
import scala.util.Random
object FileProcessing {
	var country = List[String]()
	var carMaker = Set[String]()
	var count = 1
	val random = new Random()
	def main(args: Array[String]) {
		val fileName = "D:\\scalaworkspace\\DataGenerator\\country.csv"
		country = readFromSourceAsList(fileName, fileName)
//		country.foreach(println)
		println(country(random.nextInt(country.length - 1)))
		println(country(random.nextInt(country.length - 1)))
	}
	
	/*
	read the file data from the file path to the given set
	*/
	def readFromSourceAsList(fileName: String, path: String): List[String] = {
		val bufferedSource = Source.fromFile(fileName)
		val lines = bufferedSource.getLines().toList
//		lines.foreach(println)
//		 println(country.size)
		bufferedSource.close
		lines
	}
}