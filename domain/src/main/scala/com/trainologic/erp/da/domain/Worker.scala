package com.trainologic.erp.da.domain

/**
 * Created by oridar on 2/17/2016
 */
//sealed abstract class Worker(fn: String, ln: String, uid: String, pwd: String) extends Serializable {
//
//}

sealed trait Worker extends Serializable {
  def uid: String
  def pwd: String
  def fn: String
  def ln: String
}

final case class Employee(fn: String, ln: String, uid: String, pwd: String, salary: Int) extends Worker {

}

final case class Freelancer(fn: String, ln: String, uid: String, pwd: String) extends Worker {

}