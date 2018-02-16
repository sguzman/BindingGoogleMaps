package com.github.sguzman.binding.scala.typesafe.verify

case class Rating(
                 aggregateType: String,
                 displayValue: String,
                 histogram: Map[Int, Int],
                 subject: Subject
                 )
