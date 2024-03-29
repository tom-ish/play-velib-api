package controllers

import javax.inject._
import models.LocationData
import play.api._
import play.api.libs.json.{JsObject, Json}
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: MessagesControllerComponents) extends MessagesAbstractController(cc) {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit messagesRequest: MessagesRequest[AnyContent] =>
    Ok(views.html.index(LocationData.form, routes.VelibsLocationController.getSplioVelibs().toString, ""))
  }
}
