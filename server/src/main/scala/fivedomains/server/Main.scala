package fivedomains.server

import zio._
import zio.http.*
import fivedomains.database.*
import io.getquill.jdbczio.Quill
import java.util.UUID

object Main extends ZIOAppDefault {

  val app = Http.collect[Request] {
    case  Method.GET -> Root / "text" => Response.text("Hello World!")

  }

  override val run =

    val u = MellUser(UUID.randomUUID(), "Bob")

    DataService.mellUsers.provide(
      DataService.live,
      Quill.Postgres.fromNamingStrategy(LowerCase),
      Quill.DataSource.fromPrefix("mellorator")
    )

    Server.serve(app).provide(Server.default)
}