package fivedomains.database

import zio.ZIO
import io.getquill.*
import io.getquill.jdbczio.Quill
import fivedomains.model.Assessment
import java.util.UUID
import zio.ZLayer

case class Foo(i:String)

val dbContext = new PostgresJdbcContext(LowerCase, "mellorator")


/** Startup stuff for the database connection */
def init() = {


}

class DataService(quill: Quill.Postgres[LowerCase]) {



}

object DataService {
    def mellUsers = ZIO.serviceWithZIO[DataService]

    val live = ZLayer.fromFunction(new DataService(_))
}