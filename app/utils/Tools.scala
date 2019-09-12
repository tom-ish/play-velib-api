package utils

import play.api.libs.json._

object Tools {

  def mkHtml(jsObject: JsObject, location: String): String = {
    var rslt = ""

    val records = (jsObject \ "records").get
    records match {
      case JsArray(recordsArray) => {
        val nbItems = recordsArray.length
        if (nbItems == 0)
          rslt += "There is no Velib station near the specified station"
        else {
          if (nbItems == 1)
            rslt += s"There is 1 Velib station near $location"
          else if (nbItems > 1)
            rslt += s"There are $nbItems Velibs stations near $location"

          rslt += "<br/>"
          rslt += mkTabLegends()

          recordsArray.foreach { jsValue =>
            rslt += mkVelibRecord(jsValue.as[JsObject])
          }

        }
      }
      case  _ => rslt += "error"
    }

    rslt
  }

  def mkTabLegends(): String = {
    """    <div class="row">
      |      <div class="col">station name</div>
      |      <div class="col">eDock(s)</div>
      |      <div class="col">free eDock(s)</div>
      |      <div class="col">eVelib(s)</div>
      |      <div class="col">Dock(s)</div>
      |      <div class="col">free Dock(s)</div>
      |      <div class="col">Velib(s)</div>
      |    </div>""".stripMargin
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


        rslt += """    <div class="row">
                        <div class="col">""" + stationName + """</div>
                        <div class="col">""" + nbeDock.toString() + """</div>
                        <div class="col">""" + nbFreeeDock.toString() + """</div>
                        <div class="col">""" + nbeBike.toString() + """</div>
                        <div class="col">""" + nbDock.toString() + """</div>
                        <div class="col">""" + nbFreeDock.toString() + """</div>
                        <div class="col">""" + nbBike.toString() + """</div>
                      </div>""".stripMargin


      case _ =>
        rslt += "error: js not recognized"
    }
    rslt
  }

}
