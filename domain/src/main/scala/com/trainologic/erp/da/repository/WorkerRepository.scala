package com.trainologic.erp.da.repository

import com.trainologic.erp.da.domain.{Freelancer, Employee, Worker}
import com.trainologic.erp.da.mongo.MongoHandler
import com.typesafe.scalalogging.Logger
import org.mongodb.scala
import org.mongodb.scala.{MongoCollection, Observer, Completed, Observable}
import org.mongodb.scala.bson.collection.immutable.Document
import org.slf4j.LoggerFactory

/**
 * Created by oridar on 2/28/2016
 */
class WorkerRepository {

  val logger = Logger(LoggerFactory.getLogger(classOf[WorkerRepository]))

  def save(worker : Worker) = {
    val document = Document("_id" -> worker.uid, "fn" -> worker.fn, "ln" -> worker.ln)
    logger.debug("Worker Document", document)
    
    val customized: Document = customize(worker, document)
    logger.debug("Extended Document", customized)
    
    val collection: MongoCollection[scala.Document] = MongoHandler.collection("workers")

    collection.insertOne(customized).subscribe(new Observer[Completed] {
      override def onError(e: Throwable): Unit = {
        logger.error(e.toString, e)
      }
      override def onComplete(): Unit = {}
      override def onNext(result: Completed): Unit = {}
    })
  }

  def customize(w: Worker, d: Document): Document = {
    w match {
      case e: Employee => d + ("salary" -> e.salary)
      case f: Freelancer => d
    }
  }
}
