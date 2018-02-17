package com.github.sguzman.binding.scala

import java.net.URLDecoder

import com.github.sguzman.binding.scala.typesafe.data.trip.{Loc, Pinpoint, Trip}
import com.github.sguzman.binding.scala.view.Doc
import com.thoughtworks.binding.Binding.{Var, Vars}
import com.thoughtworks.binding.{Binding, dom}
import google.maps.LatLng
import org.scalajs.dom.{Event, document, window}
import org.scalajs.dom.html.{Button, Div, Element, Table}
import org.scalajs.dom.raw.Position

import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.{JSGlobal, JSGlobalScope}
import scala.scalajs.niocharset.StandardCharsets
import scala.scalajs.niocharset.StandardCharsets.UTF_8
import view.Doc.StrWrap

object Main {
  var m: Option[google.maps.Map] = None
  val location: google.maps.Marker = new google.maps.Marker


  @js.native
  @JSGlobalScope
  object Globals extends js.Object {
    var items: js.Array[js.Object] = js.native
  }

  def main(args: Array[String]): Unit = {
    Doc.render(Var(false))

    google.maps.event.addDomListener(window, "load", js.Function {
      m = Some(new google.maps.Map("map".id, google.maps.MapOptions(
        center = new google.maps.LatLng(37.675554, -122.276105),
        zoom = 10,
        panControl = false,
        streetViewControl = false,
        mapTypeControl = false)))
      GoogleInit.init
      ""
    })
  }

}
