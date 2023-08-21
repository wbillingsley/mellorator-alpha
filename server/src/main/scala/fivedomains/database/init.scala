package fivedomains.database

import zio.ZIO
import io.getquill.*
import io.getquill.jdbczio.Quill
import fivedomains.model.Assessment
import java.util.UUID
import zio.ZLayer



case class Foo(i:String)


/** Startup stuff for the database connection */
def init() = {


}

class DataService(quill: Quill.Postgres[LowerCase]) {
    import quill.*

    def saveUser(u:MellUser) = quill.run(quote {
        query[MellUser].insertValue(lift(u))
    })

    def userById(id:UUID) = quill.run(quote {
        query[MellUser].filter(_.id == lift(id))
    })

}


object DataLayer {

    def saveUser(u:MellUser) = ZIO.serviceWithZIO[DataService](_.saveUser(u))
}
