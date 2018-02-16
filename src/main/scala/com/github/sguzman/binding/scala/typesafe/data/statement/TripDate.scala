package com.github.sguzman.binding.scala.typesafe.data.statement

import java.util.UUID

case class TripDate(
                   date: String,
                   cash_collected_total: String,
                   total: String,
                   total_earned: String,
                   trips: Array[UUID]
                   )
