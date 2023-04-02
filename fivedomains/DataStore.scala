package fivedomains

import scala.collection.mutable

val animalMap = mutable.Map.empty[AnimalId, Animal]

def animals = animalMap.values.toSeq.sortBy(_.id)


/** A data store for the five domains app */
object DataStore {

 

}