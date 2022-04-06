package models


import play.api.db.slick._
import slick.jdbc.JdbcProfile
import slick.jdbc.MySQLProfile.api._

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


  class TaskData @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
                                  (implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {


  var dataList = TableQuery[UserData]

  def addEmployee(user: User): Future[String] = {
    dbConfig.db.run(dataList += user)
      .map(res => " User successfully added")
      .recover {
        case ex: Exception => {
          println(ex.getMessage())
          ex.getMessage
        }
      }
  }

  def delete(id: String): Future[Int] = {
    dbConfig.db.run(dataList.filter(_.id === id).delete)
  }

  def update(user: User): Future[Int ] = {
    dbConfig.db
      .run(dataList.filter(_.id === user.id)
        .map(c => (c.fName, c.lName, c.mobile))
        .update(user.fName, user.lName, user.mobile)
      )
  }

  def get(id: String): Future[Option[User]] = {
    dbConfig.db.run(dataList.filter(_.id === id).result.headOption)
  }

  def ListAll: Future[Seq[User]] = {
    dbConfig.db.run(dataList.result)
  }


  }

