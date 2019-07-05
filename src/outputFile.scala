import java.io.File
import java.io.PrintWriter
import scala.io.Source
import scala.util.Random

object FileProcessing {
    val COUNTRY_PATH = "\\scalaworkspace\\DataGenerator\\country.csv"
    val CAR_MAKER = "\\scalaworkspace\\DataGenerator\\car_maker.csv"
    val NUM_RECORD = 100
    val RANDOM = new Random()
    var country = List[String]()
    var carMaker = List[String]()
    var aa = List[String]()

    def main(args: Array[String]) {
        country = readFromSourceAsList(COUNTRY_PATH)
//        println(getRandomFromList(country))
//        println(getRandomFromList(country))

        carMaker = readFromSourceAsList(CAR_MAKER)
//        println(getRandomFromList(carMaker))
//        println(getRandomFromList(carMaker))

        createTable(NUM_RECORD)
    }

    def createTable(numRecord: Int): Unit ={
        val sb = new StringBuilder
        for (row <- 1 to NUM_RECORD) {
            sb ++= row.toString
            sb += ','
            sb ++= getRandomFromList(country)
            sb += ','
            sb ++= getRandomFromList(carMaker)
            sb ++= System.lineSeparator()
        }
        val writer = new PrintWriter(new File("table.txt"))
        writer.write(sb.toString())
        writer.close()
    }
    /**
      * The given list size must > 0
      * @param list
      * @return a random element from the given list
      */
    def getRandomFromList(list: List[String]): String = {
        list(RANDOM.nextInt(list.length))
    }

    /**
      * There must be a parent folder for DataGenetor,
      * for example : "\\parentfolder\\DataGenerator\\country.csv"
      * @param path
      * @return a list of data
      */
    def readFromSourceAsList(path: String): List[String] = {
        val bufferedSource = Source.fromFile(path)
        val lines = bufferedSource.getLines().toList
        bufferedSource.close
        lines
    }

}