package fivedomains.testdata

import fivedomains.* 
import model.*

import scalajs.js


def addPickles() = 
    val pickles = DataStore.addAnimal(Animal(id = DataStore.nextAnimalId, "Pickles", Species.Horse, sex=Sex.Male, display=DisplayStyle.Plaid))

    DataStore.addAssessment(animal = pickles.id, situation = Situation.DayToDay, time = js.Date.UTC(2023, 1, 12, 18, 22, 0, 0), Seq(
            (AnswerValue.Numeric(100), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.High, None),
            (AnswerValue.Numeric(100), Confidence.VeryHigh, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Low, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(25), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(25), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),        
        )
    )

    DataStore.addAssessment(animal = pickles.id, situation = Situation.DayToDay, time = js.Date.UTC(2023, 2, 14, 18, 22, 0, 0), Seq(
            (AnswerValue.Numeric(100), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.High, None),
            (AnswerValue.Numeric(100), Confidence.VeryHigh, None),
            (AnswerValue.Numeric(85), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Low, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(15), Confidence.Medium, None),
            (AnswerValue.Numeric(55), Confidence.Medium, None),
            (AnswerValue.Numeric(55), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(25), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),           
        )
    )

    DataStore.addAssessment(animal = pickles.id, situation = Situation.DayToDay, time = js.Date.UTC(2023, 3, 10, 18, 22, 0, 0), Seq(
            (AnswerValue.Numeric(100), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.High, None),
            (AnswerValue.Numeric(100), Confidence.VeryHigh, None),
            (AnswerValue.Numeric(95), Confidence.High, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Low, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(10), Confidence.Medium, None),
            (AnswerValue.Numeric(25), Confidence.Medium, None),
            (AnswerValue.Numeric(25), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(25), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),           
        )
    )

    DataStore.addAssessment(animal = pickles.id, situation = Situation.DayToDay, time = js.Date.UTC(2023, 4, 8, 18, 22, 0, 0), Seq(
            (AnswerValue.Numeric(100), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.High, None),
            (AnswerValue.Numeric(100), Confidence.VeryHigh, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Low, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(10), Confidence.Medium, Some("His fur's getting patchy and he has a hoarse bark")),
            (AnswerValue.Numeric(25), Confidence.Medium, Some("Losing weight")),
            (AnswerValue.Numeric(35), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(25), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),           
        )
    )

    DataStore.addAssessment(animal = pickles.id, situation = Situation.DayToDay, time = js.Date.UTC(2023, 5, 8, 18, 22, 0, 0), Seq(
            (AnswerValue.Numeric(100), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.High, None),
            (AnswerValue.Numeric(100), Confidence.VeryHigh, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Low, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(20), Confidence.Medium, None),
            (AnswerValue.Numeric(45), Confidence.Medium, None),
            (AnswerValue.Numeric(55), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(25), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),           
        )
    )

    DataStore.addAssessment(animal = pickles.id, situation = Situation.DayToDay, time = js.Date.UTC(2023, 5, 8, 18, 22, 0, 0), Seq(
            (AnswerValue.Numeric(100), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.High, None),
            (AnswerValue.Numeric(100), Confidence.VeryHigh, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Low, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(40), Confidence.Medium, None),
            (AnswerValue.Numeric(65), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(25), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),           
        )
    )

    val rufus = DataStore.addAnimal(Animal(id = DataStore.nextAnimalId, "Rufus", Species.Dog, display=DisplayStyle.Circles))

    DataStore.addAssessment(animal = rufus.id, situation = Situation.DayToDay, time = js.Date.UTC(2023, 1, 12, 18, 22, 0, 0), Seq(
            (AnswerValue.Numeric(100), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.High, None),
            (AnswerValue.Numeric(100), Confidence.VeryHigh, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Low, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(25), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(25), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),        
        )
    )

    DataStore.addAssessment(animal = rufus.id, situation = Situation.DayToDay, time = js.Date.UTC(2023, 2, 14, 18, 22, 0, 0), Seq(
            (AnswerValue.Numeric(100), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.High, None),
            (AnswerValue.Numeric(100), Confidence.VeryHigh, None),
            (AnswerValue.Numeric(85), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Low, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(15), Confidence.Medium, None),
            (AnswerValue.Numeric(55), Confidence.Medium, None),
            (AnswerValue.Numeric(55), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(25), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),           
        )
    )

    DataStore.addAssessment(animal = rufus.id, situation = Situation.DayToDay, time = js.Date.UTC(2023, 3, 10, 18, 22, 0, 0), Seq(
            (AnswerValue.Numeric(100), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.High, None),
            (AnswerValue.Numeric(100), Confidence.VeryHigh, None),
            (AnswerValue.Numeric(95), Confidence.High, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Low, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(10), Confidence.Medium, None),
            (AnswerValue.Numeric(25), Confidence.Medium, None),
            (AnswerValue.Numeric(25), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(25), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),           
        )
    )

    DataStore.addAssessment(animal = rufus.id, situation = Situation.DayToDay, time = js.Date.UTC(2023, 4, 8, 18, 22, 0, 0), Seq(
            (AnswerValue.Numeric(100), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.High, None),
            (AnswerValue.Numeric(100), Confidence.VeryHigh, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Low, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(10), Confidence.Medium, None),
            (AnswerValue.Numeric(25), Confidence.Medium, None),
            (AnswerValue.Numeric(35), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(25), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),           
        )
    )

    DataStore.addAssessment(animal = rufus.id, situation = Situation.DayToDay, time = js.Date.UTC(2023, 5, 8, 18, 22, 0, 0), Seq(
            (AnswerValue.Numeric(100), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.High, None),
            (AnswerValue.Numeric(100), Confidence.VeryHigh, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Low, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(20), Confidence.Medium, None),
            (AnswerValue.Numeric(45), Confidence.Medium, None),
            (AnswerValue.Numeric(55), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(25), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),           
        )
    )

    DataStore.addAssessment(animal = rufus.id, situation = Situation.DayToDay, time = js.Date.UTC(2023, 6, 7, 18, 22, 0, 0), Seq(
            (AnswerValue.Numeric(12), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.High, None),
            (AnswerValue.Numeric(100), Confidence.VeryHigh, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(12), Confidence.Low, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(65), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(25), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),           
        )
    )

    /*

    val betsy = DataStore.addAnimal(Animal(id = DataStore.nextAnimalId, "Betsy", Species.Horse, display=DisplayStyle.Plaid))

    DataStore.addAssessment(animal = betsy.id, situation = Situation.DayToDay, time = js.Date.UTC(2023, 7, 8, 18, 22, 0, 0), Seq(
            (AnswerValue.Numeric(100), Confidence.Medium, None),
            (AnswerValue.Numeric(100), Confidence.High, None),
            (AnswerValue.Numeric(100), Confidence.VeryHigh, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(12), Confidence.Low, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(65), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(75), Confidence.Medium, None),
            (AnswerValue.Numeric(25), Confidence.Medium, None),
            (AnswerValue.Numeric(25), Confidence.Medium, None),
            (AnswerValue.Numeric(25), Confidence.Medium, None),           
        )
    )

    val empty = DataStore.addAnimal(Animal(id = DataStore.nextAnimalId, "Empty", Species.Horse, display=DisplayStyle.Plaid))
    */
