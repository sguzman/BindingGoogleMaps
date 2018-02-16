package com.github.sguzman.binding.scala.typesafe.verify

case class ProfileNav(
                     documents: Documents,
                     profile: ProfileProfile,
                     vehicles: Vehicles,
                     primarySideNav: Option[Int],
                     url: Option[String]
                     )
