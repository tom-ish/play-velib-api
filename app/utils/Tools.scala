package utils

import play.api.libs.json._

object Tools {
  val jsonTransformer = (__ \ Symbol("records")).json.pick

  def mkHtml(jsObject: JsObject): String = {
    var rslt = ""

    val records = (jsObject \ "records").get
    records match {
      case JsArray(recordsArray) =>
        recordsArray.foreach {jsValue =>
          rslt += mkVelibRecord(jsValue.as[JsObject])
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

  def mkVelibRecord(jsObject: JsObject): String = {
    var rslt = ""
    val fields = (jsObject \ "fields").get

    fields match {
      case JsString(value) => rslt += value
      case JsObject(value) => rslt += "jsObject" + value.toString()
      case JsArray(fieldsArray) =>
        rslt += "jsArray" + fieldsArray.toString()
      //        fieldsArray.foreach {
      //          rslt += _.toString() + "\n"
      //        }

      case _ => rslt += "othr errors"
    }
//    jsObject match {
//      case (value) =>
//        value match {
//          case Map("fields", fields: JsValue) =>
//        }
//        rslt += s"jsObject => ${value.toString}\n"
//      case _ => rslt += "record error"
//    }
    rslt
  }
}
