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

        

        SurveySelectWidget(a, surveys)


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


case class SurveySelectWidget(animal:Animal, surveys:Seq[Assessment]) extends DHtmlComponent {

    val max = surveys.length
    val number = stateVariable(max)

    def subset = surveys.slice(surveys.length - number.value, surveys.length)

    override def render = <.div(

        <.div(^.cls := alignCentreStyle,    
            <.p(s"Showing most recent ${number.value} of $max surveys"),
            <.p(
                <.input(
                    ^.attr("type") := "range", ^.attr("min") := 1, ^.attr("max") := max,
                    ^.prop("value") := number.value, ^.on.input ==> { e => for v <- e.inputValue do number.value = v.toInt }
                ),
            ),
        ),

        surveySummary(animal, subset)
    )

}


def surveySummary(animal:Animal, surveys:Seq[Assessment]) = <.div(
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
                assessments.colouredSparkTrend7(surveys)
            )
        ),

    <.div(^.cls := nakedParaMargins,
        assessmentQuantumStats(animal, surveys),
        warningFlags(surveys),
        lowestRatingAdvice(surveys), 
        confidenceAdvice(surveys)   
    )
)

def warningFlags(surveys:Seq[Assessment]) = <.div(
    <.p("Spot for warning flags we detect from these assessments...")
)

def lowestRatingAdvice(surveys:Seq[Assessment]) = <.div(
    <.p("Spot for highlighting the trend of questions that have been the lowest rated in recent surveys...")
)

def confidenceAdvice(surveys:Seq[Assessment]) = <.div(
    <.p("Spot for highlighting issues in confidence in recent questions...")
)