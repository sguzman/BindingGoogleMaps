package com.github.sguzman.binding.scala.typesafe.verify

case class EarningsNav(
                      banking: Banking,
                      instantPay: InstantPay,
                      paymentStatements: PaymentStatements,
                      taxes: Taxes,
                      primarySideNav: Option[Int]
                      )
