package com.github.sguzman.binding.scala

import com.thoughtworks.binding.{Binding, dom}
import google.maps.LatLng
import org.scalajs.dom.{document, window}
import org.scalajs.dom.html.{Div, Element}

import scala.scalajs.js

object Main {
  implicit final class StrWrap(str: String) {
    def id = document.getElementById(str)
    def id[A] = document.getElementById(str).asInstanceOf[A]
  }

  @dom def _render: Binding[Div] = {
    <div id="map"></div>
  }

  def render = _render

  def main(args: Array[String]): Unit = {
    dom.render(document.body, render)

    "map".id[Element].style.height = (window.screen.availHeight - 100).toString ++ "px"


    google.maps.event.addDomListener(window, "load", js.Function {
      new google.maps.Map(document.getElementById("map"), google.maps.MapOptions(
        center = new LatLng(37.675554, -122.276105),
        zoom = 10,
        panControl = false,
        streetViewControl = false,
        mapTypeControl = true))
      ""
    })
  }

}
