package com.github.sguzman.binding.scala

import java.net.URLDecoder

import com.github.sguzman.binding.scala.typesafe.data.trip.{Loc, Pinpoint, Trip}
import com.thoughtworks.binding.Binding.Vars
import io.circe.generic.auto._
import io.circe.parser.decode

import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.scalajs.niocharset.StandardCharsets.UTF_8

object GoogleInit {
  implicit final class JSWrap(j: js.Object) {
    def trip = decode[Trip](JSON.stringify(j)).right.get
  }

  def pickDrop(str: String, t: Trip) =
    URLDecoder.decode(t.customRouteMap.get, UTF_8.toString)
      .split("&")
      .toList
      .filter(_.startsWith("markers"))
      .map(_.split("\\|").last.split(",").map(_.trim.toDouble).toList)
      .map(v => Pinpoint(v.head, v.last)).toList ++ List(t)

  def pickDropSwitch(t: Trip) = t.customRouteMap match {
    case None => None
    case Some(v) => Some(pickDrop(v, t))
  }

  def parseData(j: js.Array[js.Object]) =
    (for (i <- j) yield i.trip)
      .map(pickDropSwitch)
      .filter(_.isDefined)
      .map(_.get)
      .filter(_.lengthCompare(3) == 0)
      .map(t =>
        Loc(
          t.head.asInstanceOf[Pinpoint],
          t.tail.head.asInstanceOf[Pinpoint],
          t.last.asInstanceOf[Trip]
        )
      )

  def marker(l: Loc) =
    new google.maps.Marker(
      google.maps.MarkerOptions(
        map = null,
        position = l.pick.latlng,
        title = l.trip.total
      )
    )

  def init(m: google.maps.Map, ts: Vars[Loc]) = {
    ts.value ++= parseData(JS.Globals.items)
    Main.items.append(ts.value.map(marker): _*)
    new JS.MarkerClusterer(m,
      js.Array[google.maps.Marker](Main.items: _*),
      js.Dynamic.literal(
        imagePath =
          "https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m"
      )
    )
  }
}
