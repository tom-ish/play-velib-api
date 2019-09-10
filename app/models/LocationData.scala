package models

import play.api.data.Form
import play.api.data.Forms._
import play.api.data.Forms.mapping

case class LocationData(keyword: String)
object LocationData {
  val form = Form(
    mapping(
      "location" -> nonEmptyText
    )(LocationData.apply)(LocationData.unapply)
  )
}
