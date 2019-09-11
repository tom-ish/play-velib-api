package controllers

import javax.inject.Inject
import models.{Location, LocationData}
import play.api.data.Form
import play.api.mvc.{Action, AnyContent, MessagesAbstractController, MessagesControllerComponents, MessagesRequest, Request}
import play.api.libs.ws._
import play.api.http.HttpEntity
import play.api.libs.json.JsObject
import play.filters.csrf.CSRF

import scala.concurrent.{ExecutionContext, Future}

class VelibsLocationController @Inject()(ws: WSClient, cc: MessagesControllerComponents)(implicit executionContext: ExecutionContext)
  extends MessagesAbstractController(cc)  {

  def getSplioVelibs() : Action[AnyContent] = Action.async { implicit request: MessagesRequest[AnyContent] =>
    def failure = { formWithErrors : Form[LocationData] =>
      Future.successful(BadRequest)
    }
    def success = { locationData : LocationData =>
      val location = Location(locationData.keyword)
      val apiURL = s"https://opendata.paris.fr/api/records/1.0/search/?dataset=velib-disponibilite-en-temps-reel"

      val wsRequest: WSRequest = ws.url(apiURL)
      val wsResponse = wsRequest.addQueryStringParameters(
        ("q" -> s"station_name:${location.keyword}+OR+station:${location.keyword}"),
        ("pretty_print" -> "true")
      ).get

      wsResponse map {
        case response : WSResponse =>
          Ok(views.html.index(
            LocationData.form,
            routes.VelibsLocationController.getSplioVelibs().toString,
            response.json.toString))
        case _ => MethodNotAllowed
      }
    }
    LocationData.form.bindFromRequest.fold(failure, success)
  }

}
