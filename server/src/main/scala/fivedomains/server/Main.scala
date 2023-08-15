package fivedomains.server

import zio._
import zio.http.*

object Main extends ZIOAppDefault {

  val app = Http.collect[Request] {
    case  Method.GET -> Root / "text" => Response.text("Hello World!")

  }

  override val run =
    Server.serve(app).provide(Server.default)
}