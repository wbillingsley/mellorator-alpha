package fivedomains


import scalajs.js
import fivedomains.model.Assessment

import scala.collection.mutable

import org.scalajs.dom.window.localStorage 

// Pickling library, for stringifying data to store in localStorage
import upickle.default.*
// Automatic writers for case classes
import upickle.default.{ReadWriter => RW, macroRW}
import typings.std.stdStrings.a



/**
  * Very simple internal UI analytics
  */
object Analytics {

    /* The event types we can locally record */
    enum Event:
        case PageOpen(date:Long)
        case Navigation(date:Long, destination:String)
        case SaveAssessment(date:Long, assessment:Assessment)

    
    given RW[Event] = RW.merge(macroRW[Event.PageOpen], macroRW[Event.Navigation], macroRW[Event.SaveAssessment])


    private val _analytics:mutable.Buffer[Event] = 
        val stored = Option(localStorage.getItem("analytics"))
        stored match {
            case Some(json) =>
                val parsed = read[Seq[Event]](json) 
                parsed.to(mutable.Buffer)
                //mutable.Buffer.empty[Assessment]
            case None => 
                mutable.Buffer.empty[Event]
        }


    def logEvent(event:Event) = 
        _analytics.append(event)
        localStorage.setItem("analytics", write(_analytics))


    def resetLogs() = 
        _analytics.clear()
        localStorage.setItem("analytics", write(_analytics))


}