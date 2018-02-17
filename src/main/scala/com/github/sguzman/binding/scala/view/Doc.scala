package com.github.sguzman.binding.scala.view

import com.github.sguzman.binding.scala.GoogleInit
import com.github.sguzman.binding.scala.Main.m
import com.thoughtworks.binding.Binding.Var
import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.{Event, document, window}
import org.scalajs.dom.html.{Button, Div, Element, Table}

import scala.scalajs.js

object Doc {
  implicit final class StrWrap(str: String) {
    def id = document.getElementById(str).asInstanceOf[Element]
    def style = id.style
  }

  @dom def _render(b: Var[Boolean]): Binding[Div] = {
    <div>
      <span>
        <div id="map"></div>
      </span>
    </div>
  }

  def render(b: Var[Boolean]): Unit = {
    dom.render(document.body, _render(b))
    "map".style.height = (window.screen.availHeight - 100).toString ++ "px"
    google.maps.event.addDomListener(window, "load", js.Function {
      m = Some(new google.maps.Map("map".id, google.maps.MapOptions(
        center = new google.maps.LatLng(37.675554, -122.276105),
        zoom = 10,
        panControl = false,
        streetViewControl = false,
        mapTypeControl = false)))
      GoogleInit.init(m.get)
      ""
    })
  }
}
