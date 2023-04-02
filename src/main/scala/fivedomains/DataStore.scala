package fivedomains

import scala.collection.mutable
import com.wbillingsley.veautiful.PushVariable

val animalMap = mutable.Map.empty[AnimalId, Animal]

def animals = animalMap.values.toSeq.sortBy(_.id)


/** A data store for the five domains app */
object DataStore {

    def addAnimal(a:Animal):Unit =
    animalMap(a.id) = a

    def nextAnimalId = (0 :: animals.toList.map(_.id)).max + 1

    val assessments = mutable.Buffer.empty[Assessment]

    def surveysFor(a:Animal) = assessments.toSeq.filter(_.animal == a.id)

    val acceptedSensitiveTopics = PushVariable(false) {_ => 
        if animals.isEmpty then Router.routeTo(AppRoute.AddAnimal) else Router.routeTo(AppRoute.Front)
    }

}