package com.github.sguzman.binding.scala

import java.net.URLDecoder

import com.github.sguzman.binding.scala.Main.{Globals, location, m}
import com.github.sguzman.binding.scala.typesafe.data.trip.{Loc, Pinpoint, Trip}
import org.scalajs.dom.raw.Position
import org.scalajs.dom.window

import io.circe.parser.decode
import io.circe.generic.auto._

import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.scalajs.niocharset.StandardCharsets.UTF_8

object GoogleInit {
  def geo = {
    def success(p: Position): Unit = {
      location.setMap(m.get)
      location.setPosition(new google.maps.LatLng(p.coords.latitude, p.coords.longitude))
    }

    window.navigator.geolocation.watchPosition(success)
  }

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
    (for (i <- Globals.items) yield i.trip)
      .map(pickDropSwitch)
      .filter(_.isDefined)
      .map(_.get)
      .filter(_.length == 3)
      .map(t => Loc(t.head.asInstanceOf[Pinpoint], t.tail.head.asInstanceOf[Pinpoint], t.last.asInstanceOf[Trip]))

  def init = {
    geo
    parseData(Globals.items).map(t => new google.maps.Marker(google.maps.MarkerOptions(
      map = m.get,
      position = t.pick.latlng
    )))
  }
}
