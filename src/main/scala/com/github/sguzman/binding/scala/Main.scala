package com.github.sguzman.binding.scala

import com.github.sguzman.binding.scala.view.Doc
import com.thoughtworks.binding.Binding.Var

import scala.collection.mutable
import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, JSGlobalScope}

object Main {
  var m: Option[google.maps.Map] = None
  val location: google.maps.Marker = new google.maps.Marker
  val items: mutable.ListBuffer[google.maps.Marker] = mutable.ListBuffer()

  @js.native
  @JSGlobalScope
  object Globals extends js.Object {
    val items: js.Array[js.Object] = js.native
  }

  @js.native
  class MarkerClusterer(m: google.maps.Map, a: js.Array[google.maps.Marker], j: js.Object) extends js.Object

  def main(args: Array[String]): Unit = {
    Doc.render(Var(false))
  }

}
