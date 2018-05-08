package edu.knoldus

import java.io.File
import org.scalatest.{AsyncFunSuite}

class ReadingFoldersAndFilesTest extends AsyncFunSuite {

  test("Files Present In Directory Testing unit"){

    val readingObj = new ReadingFoldersAndFiles
    val file: File = new File("/home/knoldus/Desktop/Scala 02/Scala_04_Assignment/src/umang")

    val listOfFiles = readingObj.filesInDirectory(file)
    val file1 = new File("/home/knoldus/Desktop/Scala 02/Scala_04_Assignment/src/umang/file2.txt")
    val file2 = new File("/home/knoldus/Desktop/Scala 02/Scala_04_Assignment/src/umang/file1.txt")
    val file3 =new File("/home/knoldus/Desktop/Scala 02/Scala_04_Assignment/src/umang/files/file1.txt")

    val outputListOfFiles = List(file1, file2, file3)
    listOfFiles map(listOfFiles => assert(listOfFiles == outputListOfFiles))
  }

}