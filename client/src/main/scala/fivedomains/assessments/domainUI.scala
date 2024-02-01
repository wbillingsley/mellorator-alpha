package fivedomains.assessments

import fivedomains.model.* 
import fivedomains.*

extension (d:Domain) {
    def color = d match {
        case Domain.Nutrition => darkPurple // "#d7ac2a" //"#e6ca84"
        case Domain.Environment =>  orange // "#869259" //"#5F6B6A"
        case Domain.Health => darkGreen // "#d57d87" //"#FF5F58"
        case Domain.InteractionsEnvironment => darkPurple //"#b797b8" //"#354D51"
        case Domain.InteractionsHuman => darkGreen //"#b797b8" //"#354D51"
        case Domain.InteractionsSocial => orange //"#b797b8" //"#354D51"
        case Domain.Mental => darkCream // "#629bbc" // "#4D5D38" 
    }
}