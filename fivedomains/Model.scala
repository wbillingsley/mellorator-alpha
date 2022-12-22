package fivedomains

import com.wbillingsley.veautiful.PushVariable

import scala.collection.mutable

enum DisplayStyle:
    case Curls
    case Herringbone
    case Flowers
    case Circles
    case Plaid

type AnimalId = Int
case class Animal(id:AnimalId, name:String, display:DisplayStyle = DisplayStyle.Curls)

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

val acceptedSensitiveTopics = PushVariable(false) {_ => 
    if animals.isEmpty then Router.routeTo(AppRoute.AddAnimal) else Router.routeTo(AppRoute.Front)
}

