package fivedomains.model

/**
  * A first pass at how to produce text for the advice page
  */

import QuestionIdentifier.*

val redFlagFeedback = Map[QuestionIdentifier, Animal => String] (
    DrinksEnough -> {
        (a:Animal) => s"Please ensure that water source is clean and take time to observe ${a.name} drinking to establish that their drinkng behaviour is normal"
    },
    EatsEnough -> {
        (a:Animal) => s"Please ensure that food offered has enough nutritional value and take time to observe ${a.name} is able to access and appears to enjoy the food that is offered"
    },
    FoodVariety -> {
        (a:Animal) => s"Please provide food that aligns with the feeding behaviour of this species (${a.species})"
    },
    Lighting -> {
        (a:Animal) => s"Please reduce the animal’s exposure to smells and sounds they do not like. Lighting should be as natural as possible."
    },
    ThermalComfort -> {
        (a:Animal) => s"Please improve the animal’s access to areas that suit their species-specific needs to avoid faeces and urine and to rest in a comfortable area where it can regulate its body temperature."
    },
    Sleep -> {
        (a:Animal) => s"Please improve the animal’s access to quiet areas that suit their species-specific needs to lie down. Please seek veterinary advice"
    },
    InjuriesDisease -> {
        (a:Animal) => s"Please seek veterinary advice."
    },
    Bodyweight -> {
        (a:Animal) => s"Please seek veterinary advice."
    },
    StimulatingEnvironment -> {
        (a:Animal) => s"Please seek advice on species-specific environmental enrichment"
    },
    Room -> {
        (a:Animal) => s"Please improve the animal’s access to areas that meet their species-specific needs to move as they would in a natural environment"
    },
    SameSpeciesInteraction -> {
        (a:Animal) => s"Please improve the animal’s access to other non-aggressive members of their own species."
    },
    OtherSpeciesConflict -> {
        (a:Animal) => s"Please improve the animal’s access to areas that meet their species-specific needs to avoid other animals."
    },
    OtherSpeciesInteraction -> {
        (a:Animal) => s"Please improve the animal’s access to areas that meet their species-specific needs to socialise and play with animals of other species"
    },
    CarerBond -> {
        (a:Animal) => s"Please ensure that the animal is safe, calm and content in the presence of their carer"
    },
    PeopleInteraction -> {
        (a:Animal) => s"Please ensure that the animal is safe, calm and content in the presence of familiar people  - then transition these attributes to encounters with less familiar people"
    },
    CalmWithPeople -> {
        (a:Animal) => s"Please ensure that the animal’s behaviour in the presence of most people is predictable and placid. Please ensure that the animal is safe, calm and content in the presence of familiar people"
    },
    
)