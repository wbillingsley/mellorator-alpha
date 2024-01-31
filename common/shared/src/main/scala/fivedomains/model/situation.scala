package fivedomains.model

import upickle.default.*

enum Situation derives ReadWriter:
    case DayToDay 
    case Exercising 
    case Transport 
    case Training 
    case Working
    case Competition
    case Showing
    case Veterinary

    def descr = this match {
        case DayToDay => "day-to-day living"
        case Exercising => "exercising"
        case Transport => "being transported"
        case Training => "training"
        case Working => "working"
        case Competition => "competition"
        case Showing => "showing"
        case Veterinary => "veterinary care or grooming"
    }

enum Frequencies(toText: () => String):
    case DaysPerMonth(n:Int) extends Frequencies(() => n match {
        case 0 => "less than 1 day per month"
        case 1 => "1 day per month"
        case _ => s"$n days per month"
    })
    case MoreThanDaysPerMonth(n:Int) extends Frequencies(() => n match {
        case n => s"more than $n days per month"
    })
    case DaysPerWeek(n:Int) extends Frequencies(() => n match {
        case 0 => "less than 1 day per week"
        case 1 => "1 day per week"
        case _ => s"$n days per week"
    })
    case MoreThanDaysPerWeek(n:Int) extends Frequencies(() => n match {
        case n => s"more than $n days per week"
    })
    case Weekly extends Frequencies(() => "weekly")
    case Daily extends Frequencies(() => "daily")

enum Duration(toText: () => String):
    case Hours(n:Int) extends Duration(() => n match {
        case 0 => "less than 1 hour"
        case 1 => "1 hour"
        case _ if n > 23 => "all day" 
        case n => s"$n hours"
    })
    case MoreThanHours(n:Int) extends Duration(() => n match {
        case n => s"more than $n hours"
    })

import Situation.*
import Frequencies.*
import Duration.*
import Species.*

def allowableSituations(species:Species) = species match {
    case Cat => Seq(DayToDay, Transport, Showing, Veterinary)
    case Dog => Seq(DayToDay, Transport, Exercising, Working, Competition, Veterinary)
    case Horse => Seq(DayToDay, Transport, Training, Working, Competition)
    case _ => Seq(DayToDay)
}

val dailyRange = (0 to 6).map { n => DaysPerWeek(n) }
val monthlyRange = (0 to 6).map { n => DaysPerMonth(n) }

def allowableFrequencies(species: Species, sit:Situation):Seq[Frequencies] = { (species, sit) match 
    case (_, DayToDay) => Seq(Daily)

    case (Horse, Transport) => dailyRange
    case (Horse, Training) => dailyRange
    case (Horse, Working) => dailyRange
    case (Horse, Competition) => (0 to 5).map(DaysPerMonth.apply) :+ MoreThanDaysPerMonth(5)

    case (Dog, Transport) => dailyRange
    case (Dog, Exercising) => dailyRange
    case (Dog, Working) => dailyRange
    case (Dog, Competition) => (0 to 5).map(DaysPerMonth.apply) :+ MoreThanDaysPerMonth(5)
    case (Dog, Veterinary) => (0 to 3).map(DaysPerMonth.apply) :+ MoreThanDaysPerMonth(3)

    case (Cat, Transport) => (0 to 1).map(DaysPerWeek.apply) :+ MoreThanDaysPerWeek(1)
    case (Cat, Showing) => (0 to 3).map(DaysPerWeek.apply) :+ Weekly
    case (Cat, Veterinary) => (0 to 3).map(DaysPerWeek.apply) :+ Weekly

}

def allowableDurations(species: Species, sit:Situation):Seq[Duration] = { (species, sit) match 
    case (_, DayToDay) => Seq(Hours(24))

    case (Horse, Transport) => (0 to 2).map(Hours.apply) :+ MoreThanHours(2)
    case (Horse, Training) => (0 to 2).map(Hours.apply) :+ MoreThanHours(2)
    case (Horse, Working) => (0 to 5).map(Hours.apply) :+ MoreThanHours(2)
    case (Horse, Competition) => (0 to 2).map(Hours.apply) :+ MoreThanHours(2)

    case (Dog, Transport) => (0 to 2).map(Hours.apply) :+ MoreThanHours(2)
    case (Dog, Exercising) => (0 to 2).map(Hours.apply) :+ MoreThanHours(2)
    case (Dog, Working) => (0 to 2).map(Hours.apply) :+ MoreThanHours(2)
    case (Dog, Competition) => (0 to 2).map(Hours.apply) :+ MoreThanHours(2)
    case (Dog, Veterinary) => (0 to 2).map(Hours.apply) :+ MoreThanHours(2)

    case (Cat, Transport) => (0 to 2).map(Hours.apply) :+ MoreThanHours(2)
    case (Cat, Showing) => (0 to 2).map(Hours.apply) :+ MoreThanHours(2)
    case (Cat, Veterinary) => (0 to 2).map(Hours.apply) :+ MoreThanHours(2)

}