package utils

import play.api.libs.json._

object Tools {
  val jsonTransformer = (__ \ Symbol("records")).json.pick

  def mkHtml(jsObject: JsObject): String = {
    var rslt = ""

    val records = (jsObject \ "records").get
    records match {
      case JsArray(recordsArray) =>
        recordsArray.foreach {
          rslt += mkVelibRecord(_)
        }
//        rslt += recordsArray.toString()
      case  _ => rslt += "error"
    }

//    val records = (jsObject \ "records").transform(jsonTransformer)
//    records match {
//      case JsSuccess(velibStation, path) => rslt += Json.prettyPrint(velibStation) + "\n"
//      case JsError(errors) => rslt += errors.mkString
//    }

    rslt
  }

  def mkVelibRecord(jsValue: JsValue): String = {
    var rslt = ""
    jsValue match {
      case JsNull => rslt += "null\n"
      case boolean: JsBoolean =>rslt += boolean.toString() + "\n"
      case JsNumber(value) => rslt += s"number => ${value.toString}\n"
      case JsString(value) => rslt += s"string => ${value.toString}\n"
      case JsArray(value) => rslt += s"jsArray => ${value.toString}\n"
      case JsObject(value) => rslt += s"jsObject => ${value.toString}\n"
    }
    rslt
  }
}
