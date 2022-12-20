package fivedomains

import scala.collection.mutable

type AnimalId = Int
case class Animal(id:AnimalId, species:String, name:String)

enum Domain:
    case Nutrition
    case Environment
    case Behaviour
    case Health

case class Question(num:Int, domain:Domain, text:String)

case class Answer(question:Question, value:Double)

case class Assessment(animal:AnimalId, time:Double, answers:Seq[Answer])

val animals = mutable.Buffer.empty[Animal]

def nextAnimalId = (0 :: animals.toList.map(_.id)).max + 1

val assessments = mutable.Buffer.empty[Assessment]

