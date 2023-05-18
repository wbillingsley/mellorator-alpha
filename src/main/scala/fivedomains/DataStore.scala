package fivedomains

import scala.collection.mutable
import com.wbillingsley.veautiful.PushVariable

import org.scalajs.dom.window.localStorage 

// Pickling library, for stringifying data to store in localStorage
import upickle.default.*
// Automatic writers for case classes
import upickle.default.{ReadWriter => RW, macroRW}

val animalMap = mutable.Map.empty[AnimalId, Animal]


case class DataBlob(
    acceptedSensitiveTopics:Boolean,
    animalMap:mutable.Map[AnimalId, Animal] = mutable.Map.empty[AnimalId, Animal],
    assessments:mutable.Buffer[Assessment] = mutable.Buffer.empty[Assessment]
)

/** A data store for the five domains app */
object DataStore {

    val acceptedSensitiveTopics = PushVariable(
        Option(localStorage.getItem("acceptedSensitiveTopics")).map(read[Boolean](_)).getOrElse(false)
    ) { value => 
        localStorage.setItem("acceptedSensitiveTopics", write(value))
        if animals.isEmpty then 
            Router.routeTo(AppRoute.AddAnimal) 
        else
            Router.routeTo(AppRoute.Front)
    }


    given RW[DisplayStyle] = macroRW
    given RW[Animal] = macroRW
    given RW[Confidence] = macroRW
    given RW[Answer] = macroRW
    given RW[Assessment] = macroRW

    def addAnimal(a:Animal):Unit =
        animalMap(a.id) = a
        localStorage.setItem("animalMap", write(animalMap))

    def nextAnimalId = (0 :: animals.toList.map(_.id)).max + 1

    val assessments = mutable.Buffer.empty[Assessment]

    def surveysFor(a:Animal) = assessments.toSeq.filter(_.animal == a.id)

    def animals = animalMap.values.toSeq.sortBy(_.id)
}