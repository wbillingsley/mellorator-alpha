package fivedomains.database

import io.getquill.*
import io.getquill.jdbczio.Quill


import fivedomains.model.*
import java.util.UUID

case class MellUser(id:UUID, name:String) 


class MellUserDAO(quill: Quill.Postgres[LowerCase]) {
    import quill.*

    def save(u:MellUser) = quill.run(quote {
        query[MellUser].insertValue(lift(u))
    })

    def list = quill.run(
        query[MellUser]
    )

}