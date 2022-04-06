package controllers

import models.{User, UserForm}
import play.api.libs.json.Json
import play.api.mvc._

import services.Service

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import router.RoutesPrefix._
class AppController @Inject()(val cc :ControllerComponents,
                              dataService:Service) extends AbstractController(cc) {

  implicit val jsonFormat = Json.format[User]


  def getAll: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    dataService.listAllUser map { items1 =>
      Ok(Json.toJson(items1))
    }
  }

  def getById(id: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    dataService.getUser(id) map { items2 =>
      Ok(Json.toJson(items2))
    }

  }

  def add(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>

    UserForm.form.bindFromRequest.fold(
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error"))
      },
      data => {
        val newItem = User(data.id,data.fName, data.lName, data.mobile)
        dataService.addUser(newItem).map( _ => Redirect(routes.AppController.getAll))

      })
  }

  def update() = Action.async { implicit request: Request[AnyContent] =>
    UserForm.form.bindFromRequest.fold(
      errorForm => {
        errorForm.errors.foreach(println)
        Future.successful(BadRequest("Error!"))
      },
      data => {
        val update = User(data.id, data.fName, data.lName, data.mobile)
        dataService.update(update).map( _ =>
          Redirect(routes.AppController.getAll))
      })

  }

  def delete(id: String) = Action.async { implicit request: Request[AnyContent] =>

    dataService.deleteUser(id) map { res =>
      Redirect(routes.AppController.getAll)
    }

  }
}