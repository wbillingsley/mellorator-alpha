package fivedomains.testdata

import fivedomains.* 
import model.*

import scalajs.js


def addPickles() = 
    val pickles = DataStore.addAnimal(Animal(id = DataStore.nextAnimalId, "Pickles", Species.Dog, DisplayStyle.Circles))

    DataStore.addAssessment(animal = pickles.id, time = js.Date.UTC(2023, 1, 12, 18, 22, 0, 0), Seq(
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

    DataStore.addAssessment(animal = pickles.id, time = js.Date.UTC(2023, 2, 14, 18, 22, 0, 0), Seq(
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

    DataStore.addAssessment(animal = pickles.id, time = js.Date.UTC(2023, 3, 10, 18, 22, 0, 0), Seq(
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

    DataStore.addAssessment(animal = pickles.id, time = js.Date.UTC(2023, 4, 8, 18, 22, 0, 0), Seq(
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

    DataStore.addAssessment(animal = pickles.id, time = js.Date.UTC(2023, 4, 8, 18, 22, 0, 0), Seq(
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

    DataStore.addAssessment(animal = pickles.id, time = js.Date.UTC(2023, 5, 8, 18, 22, 0, 0), Seq(
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

    pickles


val rufus = DataStore.addAnimal(Animal(id = DataStore.nextAnimalId, "Rufus", Species.Dog, DisplayStyle.Circles))
