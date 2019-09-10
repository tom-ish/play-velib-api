package models

case class Location(keyword : String)
object Location {
  import play.api.libs.json._

  implicit object LocationWrites extends OWrites[Location] {
    override def writes(location: Location): JsObject = Json.obj(
      "keyword" -> location.keyword
    )
  }

  implicit object LocationReads extends Reads[Location] {
    override def reads(json: JsValue): JsResult[Location] = json match {
      case obj: JsObject => try {
        val keyword = (obj \ "keyword").as[String]

        JsSuccess(Location(keyword))
      } catch {
        case cause: Throwable => JsError(cause.getMessage)
      }
      case _ =>
        JsError("JsObject excepted")
    }
  }
}
