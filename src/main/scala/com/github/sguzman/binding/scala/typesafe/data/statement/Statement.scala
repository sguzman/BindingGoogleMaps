package com.github.sguzman.binding.scala.typesafe.data.statement

case class Statement(
                    statusCode: Int,
                    body: Body,
                    headers: Map[String,String],
                    request: Request,

                    )
