package utils

import play.api.libs.json._

object Tools {
  val jsonTransformer = (__ \ Symbol("records")).json.pick

  def mkHtml(jsObject: JsObject): String = {
    var rslt = ""

    val records = (jsObject \ "records").get
    records match {
      case JsArray(value: JsValue) => rslt += Json.prettyPrint(value) + "\n"
      case  _ => rslt += "not found"
    }

//    val records = (jsObject \ "records").transform(jsonTransformer)
//    records match {
//      case JsSuccess(velibStation, path) => rslt += Json.prettyPrint(velibStation) + "\n"
//      case JsError(errors) => rslt += errors.mkString
//    }

    rslt
  }
}
