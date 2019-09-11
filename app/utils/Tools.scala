package utils

import play.api.libs.json._

object Tools {
  def mkHtml(jsObject: JsObject): String = {
    var rslt = ""
    val records = (jsObject \ "records").transform((__ \ Symbol("records")).json.pick)

    records match {
      case JsSuccess(velibStation, path) => rslt += Json.prettyPrint(velibStation) + "\n"
      case JsError(errors) => rslt += "error"
    }

    rslt
  }
}
