package fivedomains.server

import zio._
import zio.http.*
import fivedomains.database.*
import io.getquill.jdbczio.Quill
import java.util.UUID
import io.getquill.LowerCase

object Main extends ZIOAppDefault {

  val dataserviceLive = ZLayer.fromFunction(DataService.apply(_))
  val datasourceLive = Quill.DataSource.fromPrefix("mellorator")
  val postgresLive = Quill.Postgres.fromNamingStrategy(LowerCase)

  val app = Http.collectZIO[Request] {
    case Method.GET -> Root / "text" => 
      ZIO.succeed(Response.text("Hello World!"))

    case Method.GET -> Root / "doit" => 
      (for 
        n <- DataLayer.saveUser(MellUser(UUID.randomUUID(), "Cecily"))
        _ = println(n)
      yield Response.text(s"It was $n")).orDie
  }


  override val run =

    val u = MellUser(UUID.randomUUID(), "Bob")

    val h = (for {
      n <- DataLayer.saveUser(u)
      _ = println(n)
    } yield n).provide(dataserviceLive, datasourceLive, postgresLive)

    Server.serve(app).provide(Server.defaultWithPort(8081), dataserviceLive, datasourceLive, postgresLive)
}