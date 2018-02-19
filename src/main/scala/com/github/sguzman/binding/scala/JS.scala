package com.github.sguzman.binding.scala

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobalScope

object JS {
  @js.native
  @JSGlobalScope
  object Globals extends js.Object {
    val items: js.Array[js.Object] = js.native
  }

  @js.native
  class MarkerClusterer(m: google.maps.Map, a: js.Array[google.maps.Marker], j: js.Object) extends js.Object
}
