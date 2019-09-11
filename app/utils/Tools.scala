package utils

import play.api.libs.json._

object Tools {
  val jsonTransformer = (__ \ Symbol("records")).json.pick

  def mkHtml(jsObject: JsObject): String = {
    var rslt = ""

    val records = (jsObject \ "records").get
    records match {
      case value: JsBoolean => rslt += value.toString()
      case JsNumber(value) => rslt += value.toString()
      case JsString(value) => rslt += value
      case JsArray(value) => rslt += "array =>" + value.toString()
      case record: JsObject => rslt += Json.prettyPrint(record) + "\n"
      case  _ => rslt += "error"
    }

//    val records = (jsObject \ "records").transform(jsonTransformer)
//    records match {
//      case JsSuccess(velibStation, path) => rslt += Json.prettyPrint(velibStation) + "\n"
//      case JsError(errors) => rslt += errors.mkString
//    }

    rslt
  }
}
