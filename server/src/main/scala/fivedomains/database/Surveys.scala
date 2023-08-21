package fivedomains.database

import io.getquill.*
import io.getquill.jdbczio.Quill


import fivedomains.model.*

case class AssessmentD(animal:AnimalId, time:Double, answers:Seq[Double]) 


class SurveyDAO(quill: Quill.Postgres[LowerCase]) {


}