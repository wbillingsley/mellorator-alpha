package fivedomains.database

import io.getquill.*
import io.getquill.jdbczio.Quill
import dbContext.*


import fivedomains.model.*

case class AssessmentD(animal:AnimalId, time:Double, answers:Seq[Double]) 


class SurveyDAO(quill: Quill.Postgres[LowerCase]) {

    def save(a:AssessmentD) = dbContext.run(quote {
        query[AssessmentD].insertValue(lift(a))
    })


}