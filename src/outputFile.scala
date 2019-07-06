import java.io.File
import java.io.PrintWriter
import java.time.LocalDate
import scala.io.Source
import scala.util.Random

object FileProcessing {
    val COUNTRY_PATH = "\\scalaworkspace\\DataGenerator\\country.csv"
    val CAR_MAKER = "\\scalaworkspace\\DataGenerator\\car_maker.csv"
    val DATE = "\\scalaworkspace\\DataGenerator\\date.txt"
    val OUTPUT_FILE = "table.txt"
    val NUM_RECORD = 100
    val RANDOM = new Random()

    var startDate =  LocalDate.now()
    var endDate = LocalDate.now()

    var country = List[String]()
    var carMaker = List[String]()

    def main(args: Array[String]) {
        country = readFromSourceAsList(COUNTRY_PATH)
//        println(getRandomFromList(country))
//        println(getRandomFromList(country))

        carMaker = readFromSourceAsList(CAR_MAKER)
//        println(getRandomFromList(carMaker))
//        println(getRandomFromList(carMaker))

        setStartDateEndDate(DATE)
        outputFile(NUM_RECORD, OUTPUT_FILE)

    }

    /**
      * File format example
      * start date:YYYY-MM-DD
      * @param path
      */
    def setStartDateEndDate(path: String): Unit = {
        val lines: List[String] = Source.fromFile(path).getLines.toList
        startDate = LocalDate.parse(lines(0).split(":")(1))
        endDate = LocalDate.parse(lines(1).split(":")(1))
//        println(startDate.toEpochDay)
    }

    /**
      *  call after setStartDateEndDate
      * @param start
      * @param end
      * @return a random between start date and end date such as 2017-09-09
      */
    def getRandomDateBetween(start: LocalDate, end: LocalDate): LocalDate = {
        LocalDate.ofEpochDay(RANDOM.between(start.toEpochDay, end.toEpochDay + 1))
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
        val list = bufferedSource.getLines().toList
        bufferedSource.close
        list
    }

    /**
      * Override if needed, the output file as following
      * format 1,country1,carmaker1
      *        2,country2,carmaker2
      * @param numRecord the number of record generated
      */
    def outputFile(numRecord: Int, pathName: String): Unit ={
        val sb = new StringBuilder
        for (row <- 1 to NUM_RECORD) {
            sb ++= row.toString
            sb += ','
            sb ++= getRandomFromList(country)
            sb += ','
            sb ++= getRandomFromList(carMaker)
            sb += ','
            sb ++= getRandomDateBetween(startDate, endDate).toString
            sb ++= System.lineSeparator()
        }
        val writer = new PrintWriter(new File(pathName))
        writer.write(sb.toString())
        writer.close()
    }
}