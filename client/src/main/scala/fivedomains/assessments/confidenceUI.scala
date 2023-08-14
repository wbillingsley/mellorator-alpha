package fivedomains.assessments

import fivedomains.model.Confidence
import fivedomains.*

extension(c:Confidence) {
    def colourStyle = 
        if c.value < 0.2 then fgVeryPoor
        else if c.value < 0.4 then fgPoor
        else if c.value < 0.6 then fgNeutral
        else if c.value < 0.8 then fgGood
        else fgVeryGood
}