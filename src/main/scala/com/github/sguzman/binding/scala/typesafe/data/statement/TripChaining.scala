package com.github.sguzman.binding.scala.typesafe.data.statement

import java.util.UUID

case class TripChaining(
                       chain_index: Option[String],
                       normalized_distance: Double,
                       chain_uuid: UUID,
                       normalized_duration: Option[Long]
                       )
