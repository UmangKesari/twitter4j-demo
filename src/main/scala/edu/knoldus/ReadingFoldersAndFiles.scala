package edu.knoldus

import java.io.File
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class ReadingFoldersAndFiles
{

  def filesInDirectory(filename: File): Future[List[File]] = Future {

    def listFilesInDirectory(dir: File): List[File] = {

      val subDirectoryFiles: List[File] = dir.listFiles.filter(_.isDirectory).toList
      val files: List[File] = dir.listFiles.filter(_.isFile).toList

      files ::: subDirectoryFiles.flatMap(listFilesInDirectory(_))
    }

    listFilesInDirectory(filename)


}



}
