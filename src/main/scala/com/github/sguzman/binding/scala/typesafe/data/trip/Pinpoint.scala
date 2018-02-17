package com.github.sguzman.binding.scala.typesafe.data.trip

case class Pinpoint(
                 lat: Double,
                 lng: Double
                 ) {
  def latlng = new google.maps.LatLng(lat, lng)
}
