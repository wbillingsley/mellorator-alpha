package fivedomains

import com.wbillingsley.veautiful.*
import html.{Styling, VHtmlContent, DHtmlComponent, <, ^, EventMethods}

val surveyQstyle = Styling(
    """"""
).modifiedBy(
    " label.sa,label.sd" -> "display: inline-block; width: 50px; margin: 0.5em;",
    " input" -> "width: 40%; margin: 2em;",
).register()

case class AssessmentForm(animal:Animal) extends DHtmlComponent {

    def render = <.div(
        leftBlockHeader(
            Router.path(AppRoute.Front),
            "Assessment",
            <.label(^.cls &= Seq(animalName), animal.name)
        ),

        <.p(^.style := "margin: 1em;",
            s"Thinking of ${animal.name}, what is your level of agreement with each of the following statements",
        ),

        <.div(
            for ((title, domain), questions) <- allQuestions yield 
                val dc = domainColour(domain)
                <.div(^.cls &= Seq(surveyQstyle), 
                    <.div(^.style := s"padding: 5px 1em; background: $dc",
                        <.label(^.style := "color: white", title),
                    ),
                    
                    for q <- questions yield <.div(^.style := s"border-bottom: 1px solid $dc",
                        <.p(^.style := "margin: 1em;", q.text(animal)),
                        <.div(^.style := "text-align: center",
                            <.label(^.cls := "sd", "Strongly disagree"),
                            <.input(^.attr("type") := "range"),
                            <.label(^.cls := "sa", "Strongly agree"),
                        ),
                        <.p(^.style := "margin: 1em;", " "),                        
                    )
                )

        )

    )
}


def assessmentPage(aId: AnimalId) = 
    val a = animalMap(aId)
    AssessmentForm(a)