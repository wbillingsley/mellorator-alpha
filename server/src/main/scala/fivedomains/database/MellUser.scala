package fivedomains.database

import io.getquill.*
import io.getquill.jdbczio.Quill
import dbContext.*


import fivedomains.model.*
import java.util.UUID

case class MellUser(id:UUID, name:String) 


class MellUserDAO(quill: Quill.Postgres[LowerCase]) {

    def save(u:MellUser) = dbContext.run(quote {
        query[MellUser].insertValue(lift(u))
    })

    def list = dbContext.run(
        query[MellUser]
    )

}