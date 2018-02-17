package com.github.sguzman.binding.scala

import com.github.sguzman.binding.scala.view.Doc
import com.github.sguzman.binding.scala.view.Doc.StrWrap
import com.thoughtworks.binding.Binding.Var
import org.scalajs.dom.window

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobalScope

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
  }

}
