package fivedomains.assessments

import fivedomains.model.* 
import fivedomains.*

extension (d:Domain) {
    def color = d match {
        case Domain.Nutrition => "#e6ca84"
        case Domain.Environment => "#5F6B6A"
        case Domain.Health => "#FF5F58"
        case Domain.InteractionsEnvironment => "#354D51"
        case Domain.InteractionsHuman => "#354D51"
        case Domain.InteractionsSocial => "#354D51"
        case Domain.Mental => "#4D5D38" 
    }
}