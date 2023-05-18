package fivedomains.model

enum Rating(val value:Double, val abbreviation:String) derives upickle.default.ReadWriter:
    case VeryPoor extends Rating(0.0, "VP")
    case Poor extends Rating(25, "P")
    case Neutral extends Rating(50, "N")
    case Good extends Rating(75, "G")
    case VeryGood extends Rating(100, "VG")