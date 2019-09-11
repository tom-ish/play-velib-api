package utils

import play.api.libs.json._

object Tools {

  def mkHtml(jsObject: JsObject, location: String): String = {
    var rslt = ""

    val records = (jsObject \ "records").get
    records match {
      case JsArray(recordsArray) =>
        val nbItems = recordsArray.length
        if(nbItems == 0)
          rslt += "There is no Velib station near the specified station"
        else if(nbItems == 1)
          rslt += s"There is 1 Velib station near $location"
        else if(nbItems > 1)
          rslt += s"There are $nbItems Velibs stations near $location"

        recordsArray.foreach {jsValue =>
          rslt += "<br/>" + mkVelibRecord(jsValue.as[JsObject]) + "<br/>"
        }
        rslt += nbItems
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

        rslt += s"this station contains:<br/>" +
          s"${nbDock.toString()} Velibs docks <br/>" +
          s"${nbFreeDock.toString()} Velibs docks free <br/>" +
          s"${nbBike.toString()} Velibs available <br/>"
      case _ =>
        rslt += "error: js not recognized"
    }
    rslt
  }

}
