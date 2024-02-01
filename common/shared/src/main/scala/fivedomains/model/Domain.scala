package fivedomains.model

import fivedomains.*

enum Domain(val title:String):
    case Nutrition extends Domain("Nutrition")
    case Environment extends Domain("Environment")
    case Health extends Domain("Health")
    case InteractionsEnvironment extends Domain("Interactions with the environment")
    case InteractionsSocial extends Domain("Social interactions")
    case InteractionsHuman extends Domain("Interactions with humans")
    case Mental extends Domain("Overall wellbeing")

object Domain:
    val scoredDomains = Seq(Nutrition, Environment, Health, InteractionsEnvironment, InteractionsSocial, InteractionsHuman)
