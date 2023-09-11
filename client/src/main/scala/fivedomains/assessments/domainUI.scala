package fivedomains.assessments

import fivedomains.model.* 
import fivedomains.*

extension (d:Domain) {
    def color = d match {
        case Domain.Nutrition => "#d7ac2a" //"#e6ca84"
        case Domain.Environment =>  "#869259" //"#5F6B6A"
        case Domain.Health => "#d57d87" //"#FF5F58"
        case Domain.InteractionsEnvironment => "#b797b8" //"#354D51"
        case Domain.InteractionsHuman => "#b797b8" //"#354D51"
        case Domain.InteractionsSocial => "#b797b8" //"#354D51"
        case Domain.Mental => "#629bbc" // "#4D5D38" 
    }
}