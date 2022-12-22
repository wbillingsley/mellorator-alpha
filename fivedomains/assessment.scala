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

    val flattenedQs = for 
        (_, qs) <- allQuestions
        q <- qs
    yield q

    val answers = stateVariable(
        (for q <- flattenedQs yield q.num -> Answer(q.num, 50)).toMap
    )

    // Animates scrolling the next question to the top of the screen
    // 20 is an arbitrary offset so the question isn't hard against the top border
    def scrollQIntoView(num:Int):Unit = 
        for e <- domNode do
            val start = org.scalajs.dom.window.scrollY
            val target = e.querySelector(s"#question$num").asInstanceOf[org.scalajs.dom.html.Element].getBoundingClientRect().y + start - 20 
            animateProperty(start, target, 10) { (i:Double) => 
                org.scalajs.dom.window.scrollTo(0, i.toInt)
            }

    val maxQNum = 18 // TODO: Don't hardcode this

    def submit():Unit = 
        assessments.append(Assessment(animal.id, new scalajs.js.Date().valueOf, answers.value))
        Router.routeTo(AppRoute.Front)

    def render = 
      <.div(
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
                    
                    for q <- questions yield <.div(^.style := s"border-bottom: 1px solid $dc", ^.attr("id") := s"question${q.num}",
                        <.p(^.style := "margin: 1em;", q.text(animal)),
                        <.div(^.style := "text-align: center",
                            <.label(^.cls := "sd", "Strongly disagree"),
                            <.input(^.attr("type") := "range", 
                              for a <- answers.value.get(q.num) yield ^.prop("value") := a.value.toString,
                              ^.onInput ==> { (e) => for v <- e.inputValue do answers.value = answers.value.updated(q.num, Answer(q.num, v.toDouble)) }
                            ),
                            <.label(^.cls := "sa", "Strongly agree"),
                        ),
                        <.p(^.style := "margin: 1em;", " "),
                        if q.num < maxQNum then <.div(^.style := "text-align: right; margin: 1em;",
                            <.button(^.cls &= Seq(button), "Next ↓", ^.onClick --> scrollQIntoView(q.num + 1))
                        ) else Seq()                 
                    )
                )

        ),

        <.div(^.style := "text-align: right; margin: 1em;",
            <.button(^.cls &= Seq(button, primary), "Submit", ^.onClick --> submit())
        )

    )
}


def assessmentPage(aId: AnimalId) = 
    val a = animalMap(aId)
    AssessmentForm(a)