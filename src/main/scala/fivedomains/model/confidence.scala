package fivedomains.model

import fivedomains.*

/** Confidence as a value between 0 and 1 */
case class Confidence(value: Double) {
    def colourStyle:String = {
        if value < 0.2 then fgVeryPoor
        else if value < 0.4 then fgPoor
        else if value < 0.6 then fgNeutral
        else if value < 0.8 then fgGood
        else fgVeryGood
    }

    def abbreviation:String = {
        if value < 0.2 then "VL"
        else if value < 0.4 then "L"
        else if value < 0.6 then "M"
        else if value < 0.8 then "H"
        else "VH"
    }


}

object Confidence {
    val Medium = Confidence(0.5)
}