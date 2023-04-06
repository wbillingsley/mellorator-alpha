package fivedomains

import com.wbillingsley.veautiful.*
import html.{Styling, VHtmlContent, DHtmlComponent, EventMethods}

val surveyQstyle = Styling(
    """"""
).modifiedBy(
    " label.sa,label.sd" -> "display: inline-block; width: 50px; margin: 0.5em;",
    " input" -> "width: 40%; margin: 2em;",
).register()

case class AssessmentForm(animal:Animal) extends DHtmlComponent {
    import html.{<, ^}

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
        Router.routeTo(AppRoute.Animal(animal.id))

    def render = 
      <.div(
        leftBlockHeader(
            Router.path(AppRoute.Front),
            "Assessment",
            <.label(^.cls := (animalName), animal.name)
        ),

        <.p(^.style := "margin: 1em;",
            s"Thinking of ${animal.name}, what is your level of agreement with each of the following statements",
        ),

        <.div(
            for ((title, domain), questions) <- allQuestions yield 
                val dc = domainColour(domain)
                <.div(^.cls := (surveyQstyle), 
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

                        <.div(^.style := "text-align: center",
                            <.div({
                                val a = answers.value.get(q.num)
                                for level <- Confidence.values yield
                                    if a.exists(_.confidence == level) then
                                        <.button(^.attr.disabled := "disabled", ^.attr.style := "background: orange",
                                        level.abbreviation)
                                    else
                                        <.button(
                                            ^.on.click --> { answers.value = answers.value.updated(q.num, Answer(q.num, a.map(_.value).getOrElse(50), level)) },
                                            level.abbreviation
                                        )
                            }),
                            <.label(^.cls := "conf", "Confidence"),
                        ),

                        <.p(^.style := "margin: 1em;", " "),
                        if q.num < maxQNum then <.div(^.style := "text-align: right; margin: 1em;",
                            <.button(^.cls := (button), "Next ↓", ^.onClick --> scrollQIntoView(q.num + 1))
                        ) else Seq()                 
                    )
                )

        ),

        <.div(^.style := "text-align: right; margin: 1em;",
            <.button(^.cls := (button, primary), "Submit", ^.onClick --> submit())
        )

    )
}


def assessmentPage(aId: AnimalId) = 
    val a = animalMap(aId)
    AssessmentForm(a)


/**
  * Draws a "five box" diagram. Takes a map from each domain to a CSS colour (for the box) and VHtmlContent to show
  *
  */
def fiveBox(data: Map[Domain, (String, VHtmlContent)]) = 
    val gap = 10
    val boxW = 400
    val boxH = 100
    val circleR = 140

    def emptyContent = html.<.p("")
    def emptyCol = "gainsboro"

    val (nCol, nCont) = data.getOrElse(Domain.Nutrition, (emptyCol, emptyContent))
    val (eCol, eCont) = data.getOrElse(Domain.Environment, (emptyCol, emptyContent))
    val (hCol, hCont) = data.getOrElse(Domain.Health, (emptyCol, emptyContent))
    val (bCol, bCont) = data.getOrElse(Domain.Behaviour, (emptyCol, emptyContent))
    val (mCol, mCont) = data.getOrElse(Domain.Mental, (emptyCol, emptyContent))

    def mask = 
        import svg.*
        SVG("mask")(
            ^.attr("id") := "logo-mask",
            sparkbox("white", boxW - gap/2, boxH - gap/2)(^.attr("x") := 0, ^.attr("y") := 0),
            sparkbox("white", boxW - gap/2, boxH - gap/2)(^.attr("x") := boxW + gap/2, ^.attr("y") := 0),
            sparkbox("white", boxW - gap/2, boxH - gap/2)(^.attr("x") := 0, ^.attr("y") := boxH + gap/2),
            sparkbox("white", boxW - gap/2, boxH - gap/2)(^.attr("x") := boxW + gap/2, ^.attr("y") := boxH+gap/2),
            circle(^.attr("fill") := "white", ^.attr("cx") := boxW, ^.attr("cy") := boxH, ^.attr("r") := circleR, ^.attr("stroke") := "black", ^.attr("stroke-width") := gap),
        )

    import svg.*
    svg(^.attr("viewBox") := s"0 ${boxH - circleR} ${2 * boxW} ${2 * circleR}",
        mask,
        g(^.attr("mask") := "url(#logo-mask)",
            sparkbox(nCol, boxW - gap/2, boxH - gap/2)(^.attr("x") := 0, ^.attr("y") := 0),
            foreignObject(^.attr("x") := 0, ^.attr("y") := 0, ^.attr("width") := boxW - boxH, ^.attr("height") := boxH, nCont),
            sparkbox(eCol, boxW - gap/2, boxH - gap/2)(^.attr("x") := boxW + gap/2, ^.attr("y") := 0),
            foreignObject(^.attr("x") := boxW + gap/2 + boxH, ^.attr("y") := 0, ^.attr("width") := boxW - boxH, ^.attr("height") := boxH, eCont),
            sparkbox(hCol, boxW - gap/2, boxH - gap/2)(^.attr("x") := 0, ^.attr("y") := boxH + gap/2),
            foreignObject(^.attr("x") := 0, ^.attr("y") := boxH + gap/2, ^.attr("width") := boxW - boxH, ^.attr("height") := boxH, hCont),
            sparkbox(bCol, boxW - gap/2, boxH - gap/2)(^.attr("x") := boxW + gap/2, ^.attr("y") := boxH+gap/2),
            foreignObject(^.attr("x") := boxW + gap/2 + boxH, ^.attr("y") := boxH+gap/2, ^.attr("width") := boxW - boxH, ^.attr("height") := boxH, bCont),
            circle(^.attr("fill") := mCol, ^.attr("cx") := boxW, ^.attr("cy") := boxH, ^.attr("r") := circleR),
            foreignObject(^.attr("x") := boxW - boxH, ^.attr("y") := boxH - boxH/2, ^.attr("width") := 2 * boxH, ^.attr("height") := boxH, mCont),
        )
    )