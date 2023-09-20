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


def currentRedFlags(assess:Assessment) = 
    val animal = DataStore.animalMap(assess.animal)
    <.div(^.cls := nakedParaMargins,
        <.h3("Current concerns"),

        if assess.domainsContainingConcern.isEmpty then <.p("None") else None,

        // For each domain, if there exists a value scored low, show the section
        for d <- assess.domainsContainingConcern yield <.div(
            <.h4(d.title),
            for a <- assess.lowAnswers if a.question.domain == d yield <.div(
                <.h5(a.question.headline(animal)),
                <.p(a.value.labelText),
                for n <- a.note yield <.p(^.style := "font-style: italic;", n)
            )
        )        

    )

def pastRedFlags(assessments:Seq[Assessment]):DHtmlModifier = 
    if assessments.isEmpty then 
        None
    else
        val animal = DataStore.animalMap(assessments.head.animal)

        <.div(
            for assess <- assessments.sortBy(- _.time) if assess.domainsContainingConcern.nonEmpty yield <.div(^.cls := nakedParaMargins,

                <.h3(new scalajs.js.Date(assess.time).toLocaleDateString),

                // For each domain, if there exists a value scored low, show the section
                for d <- assess.domainsContainingConcern yield <.div(
                    <.h4(d.title),
                    for a <- assess.lowAnswers if a.question.domain == d yield <.div(
                        <.h5(a.question.headline(animal)),
                        <.p(a.value.labelText),
                        for n <- a.note yield <.p(^.style := "font-style: italic;", n)
                    )
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
            ),

            currentRedFlags(s),
        )
    else
        <.div(^.style := "margin: 1.5em;",
            <.div(^.style := "text-align: center;",
                assessments.colouredSparkTrend7(surveys)
            ),

            currentRedFlags(surveys.last),

            pastRedFlags(surveys.dropRight(1))
        ),

    <.div(^.cls := nakedParaMargins,
        // assessmentQuantumStats(animal, surveys),
        // warningFlags(surveys),
        // lowestRatingAdvice(surveys), 
        // confidenceAdvice(surveys)   
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