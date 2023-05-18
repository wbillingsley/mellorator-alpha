package fivedomains.model

import upickle.default.ReadWriter

/** The display style is purely decorative, as placeholder because at the moment we don't have iconography to make animals disinct */
enum DisplayStyle derives ReadWriter:
    case Curls
    case Herringbone
    case Flowers
    case Circles
    case Plaid

/** Used internally to track animals etc */
type AnimalId = Int

case class Animal(id:AnimalId, name:String, display:DisplayStyle = DisplayStyle.Curls)


