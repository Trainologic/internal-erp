package com.trainologic.erp.da.mongo

import org.mongodb.scala.{Document, MongoCollection, MongoClient}

/**
 * Created by oridar on 2/17/2016
 */
object MongoHandler {

  val mongoClient: MongoClient = MongoClient()
  val db = mongoClient.getDatabase("erp")

  def collection(name : String) = {
    db.getCollection(name)
  }

}
