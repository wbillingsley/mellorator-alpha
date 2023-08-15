package fivedomains.animals

import fivedomains.{given, *}
import model.*
import com.wbillingsley.veautiful.*
import html.*
import assessments.*


/**
 * A short summary message indicating how many assessments we've got on file, etc
 */
def assessmentQuantumStats(a:Animal, surveys:Seq[Assessment]) =
    // TODO - check if many of the surveys have been with low confidence
    <.div(^.cls := nakedParaMargins,
        <.p(
            s"There's ${surveys.length} assessments on file for ${a.name}. ",
            f"Overall confidence across the surveys was ${surveys.overallConfidence * 100}%.0f%%."
        ),
        <.p(
            for s <- surveys.lastOption.toSeq yield <.div(
                s"The most revent survey was on ${new scalajs.js.Date(s.time).toLocaleDateString}. ",
                f"Overall confidence in this survey was ${s.overallConfidence * 100}%.0f%%.",
            )

        )
        
    ) 


def animalDetailsPage(aId:AnimalId) = 
    val a = DataStore.animal(aId)
    val surveys = DataStore.surveysFor(a)

    <.div(
        leftBlockHeader(
            Router.path(AppRoute.Front),
            "Animal details",
            <.label(^.cls := (animalName), a.name)
        ),

        assessmentQuantumStats(a, DataStore.surveysFor(a)),

        // Widget for surveys
        if surveys.isEmpty then 
            assessments.sevenBox(Map.empty)(^.style := "")
        else if surveys.length == 1 then
            val s = surveys.head
            <.div(^.style := "margin: 1.5em;",
                <.div(^.style := "text-align: center;",
                    assessments.scoreText7(s)
                )
            )
        else
            <.div(^.style := "margin: 1.5em;",
                <.div(^.style := "text-align: center;",
                    assessments.sparkTrend7(surveys)
                )
            ),

        <.div(^.style := s"padding: 5px 1em; background: ${Domain.Mental.color}",
                        <.label(^.style := "color: white", Domain.Mental.toString),
        ),

        <.div(^.style := "margin: 1.5em;",
            <.p("...trend of last surveys...?")
        ),

        /*for d <- Seq(Domain.Nutrition, Domain.Environment, Domain.Health, Domain.Behaviour) yield <.div(
            <.div(^.style := s"padding: 5px 1em; background: ${domainColour(d)}",
                            <.label(^.style := "color: white", d.toString),
            ),

            <.div(^.style := "margin: 1.5em;",
                <.p("...trend of last surveys...?")
            ),
        )
                */



    )