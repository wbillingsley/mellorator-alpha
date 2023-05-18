package fivedomains.model

import fivedomains.*

enum Domain(val title:String, val color:String):
    case Nutrition extends Domain("Nutrition", nutritionCol)
    case Environment extends Domain("Environment", environmentCol)
    case Health extends Domain("Health", healthCol)
    case InteractionsEnvironment extends Domain("Interactions with the environment", behaviourCol)
    case InteractionsSocial extends Domain("Social interactions", behaviourCol)
    case InteractionsHuman extends Domain("Interactions with humans", behaviourCol)
    case Mental extends Domain("Overall wellbeing", mentalCol)
