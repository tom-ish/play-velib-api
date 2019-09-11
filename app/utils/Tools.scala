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
      case  _ => rslt += "error"
    }

    rslt
  }

  def mkVelibRecord(jsObject: JsObject): String = {
    var rslt = ""
    val fields = (jsObject \ "fields").get

    fields match {
      case velibStationObject : JsObject =>
        val stationName = (velibStationObject \ "station_name").get
        val nbeDock = (velibStationObject \ "nbedock").get
        val nbFreeeDock = (velibStationObject \ "nbfreeedock").get
        val nbeBike = (velibStationObject \ "nbebike").get

        val nbDock = (velibStationObject \ "nbdock").get
        val nbFreeDock = (velibStationObject \ "nbfreedock").get
        val nbBike = (velibStationObject \ "nbbike").get

        rslt += s"Station ${stationName}:<br/>" +
          s"  eDocks :<br/>" +
          s"${nbeDock.toString()} Velibs eDocks <br/>" +
          s"${nbFreeeDock.toString()} Velibs eDocks free <br/>" +
          s"${nbeBike.toString()} Velibs eBikes available <br/>" +
          s"  Docks :<br/>" +
          s"${nbDock.toString()} Velibs Docks <br/>" +
          s"${nbFreeDock.toString()} Velibs Docks free <br/>" +
          s"${nbBike.toString()} Velibs Bikes available <br/>"
      case _ =>
        rslt += "error: js not recognized"
    }
    rslt
  }

}
