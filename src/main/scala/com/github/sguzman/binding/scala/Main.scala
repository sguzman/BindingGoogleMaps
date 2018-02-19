package com.github.sguzman.binding.scala

import com.github.sguzman.binding.scala.view.Doc
import com.thoughtworks.binding.Binding.Var

import scala.collection.mutable

object Main {
  var m: Option[google.maps.Map] = None
  val items: mutable.ListBuffer[google.maps.Marker] = mutable.ListBuffer()

  def main(args: Array[String]): Unit = {
    Doc.render(Var(false))
  }

}
