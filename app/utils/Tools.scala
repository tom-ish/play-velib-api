package utils

import play.api.libs.json._

object Tools {

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

    rslt
  }

  def mkVelibRecord(jsObject: JsObject): String = {
    var rslt = ""
    val fields = (jsObject \ "fields").get

    fields match {
      case velibStationObject : JsObject =>
        val nbDock = (velibStationObject \ "nbedock").get
        val nbFreeDock = (velibStationObject \ "nbfreeedock").get
        val nbBike = (velibStationObject \ "nbbike").get
        val stationState = (velibStationObject \ "station_state").get
        rslt += s"this station contains:\n ${nbDock.toString()} Velibs docks \n${nbFreeDock.toString()} Velibs docks free\n" +
          s"${nbBike.toString()}\n "

      //        rslt += "jsObject" + value.toString()

      case _ =>
        rslt += "error: js not recognized"
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
