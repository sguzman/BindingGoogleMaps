package com.github.sguzman.binding.scala.view

import com.github.sguzman.binding.scala.GoogleInit
import com.github.sguzman.binding.scala.Main.m
import com.github.sguzman.binding.scala.typesafe.data.trip.Loc
import com.thoughtworks.binding.Binding.{Var, Vars}
import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.html.{Div, Element}
import org.scalajs.dom.{document, window}

import scala.scalajs.js

object Doc {
  implicit final class AnyWrap[A](a: A) {
    def as[B] = a.asInstanceOf[B]
  }

  implicit final class StrWrap(str: String) {
    def id = document.getElementById(str).asInstanceOf[Element]
    def style = id.style
  }


  @dom def _render(b: Var[Boolean], ts: Vars[Loc]): Binding[Div] = {
    <div>
      <div id="map"></div>
    </div>
  }

  def render(b: Var[Boolean]): Unit = {
    val trips = Vars.empty[Loc]
    dom.render(document.body, _render(b, trips))
    "map".style.height = (window.screen.availHeight - 100).toString ++ "px"

    google.maps.event.addDomListener(window, "load", js.Function {
      m = Some(new google.maps.Map("map".id, google.maps.MapOptions(
        center = new google.maps.LatLng(37.675554, -122.276105),
        zoom = 10,
        panControl = false,
        streetViewControl = false,
        mapTypeControl = false)))
      GoogleInit.init(m.get, trips)
      ""
    })
  }
}
