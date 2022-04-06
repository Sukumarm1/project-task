package services

import models.{TaskData, User}

import javax.inject.Inject
import scala.concurrent.Future

class Service @Inject()(items: TaskData) {

  def addUser(item: User): Future[String] = {
    items.addEmployee(item)
  }

  def deleteUser(id: String): Future[Int] = {
    items.delete(id)
  }

  def update(item: User): Future[Int] = {
    items.update(item)
  }

  def getUser(id: String): Future[Option[User]] = {
    items.get(id)
  }

  def listAllUser: Future[Seq[User]] = {
    items.ListAll
  }

}
