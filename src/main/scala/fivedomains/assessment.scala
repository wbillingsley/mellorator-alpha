package fivedomains

import com.wbillingsley.veautiful.*
import html.{Styling, VHtmlContent, DHtmlComponent, EventMethods}

val surveyQstyle = Styling(
    """"""
).modifiedBy(
    " label.sa,label.sd" -> "display: inline-block; width: 50px; margin: 0.5em;",
    " input" -> "width: 40%; margin: 2em;",
).register()


def confidenceButton(confidence: => Confidence, state:PushVariable[FooterSelection]) = {
    import html.{<, ^}

    <.button(^.cls := (button, if state.value == FooterSelection.Confidence then "selected" else ""),
        <.div(
            <.div("Confidence", ^.style := "font-size: 0.8em;"), 
            <.div(f" ${confidence.value * 100}%2.0f", "%", ^.style := s"color: ${confidence.colourStyle}"),
        ), 
        ^.onClick --> { if state.value == FooterSelection.Confidence then state.value = FooterSelection.None else state.value = FooterSelection.Confidence }
    )
}

val confidenceSliderStyle = Styling(
    """|
       |""".stripMargin
).modifiedBy(
    " input[type=range]::-webkit-slider-thumb" -> "background: orange;",
)


enum FooterSelection:
    case None
    case Confidence

def confidenceSlider(confidence:Confidence)(update: (Confidence) => Unit) = {
    import html.{<, ^}

    <.div(^.style := "text-align: center", ^.cls := confidenceSliderStyle,
        <.label(^.cls := "conf", "Confidence"),
        <.div(
            <.label(^.cls := "sd", "Low"),
            <.input(^.attr("type") := "range", 
                ^.prop.value := (confidence.value * 100).floor.toString,
                ^.prop.min := "0", ^.prop.max := "100", ^.prop.step := "1",
                ^.onInput ==> { (e) => for v <- e.inputValue do update(Confidence(v.toDouble / 100)) }
            ),
            <.label(^.cls := "sa", "High"),
        ),
    )
}

val questionFooterStyle = Styling(
    """
      |display: flex;
      |flex-direction: row;
      |justify-content: space-between;
      |align-items: center;
      |margin: 1em;
      |""".stripMargin
).modifiedBy().register()

case class AssessmentForm(animal:Animal) extends DHtmlComponent {
    import html.{<, ^}

    val answers = stateVariable(
        (for q <- flattenedQs yield q.num -> Answer(q.num, 50)).toMap
    )

    val footerSelectors = (for q <- flattenedQs yield q.num -> stateVariable[FooterSelection](FooterSelection.None)).toMap

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
            for (domain, questions) <- allQuestions yield 
                val dc = domain.color
                <.div(^.cls := (surveyQstyle), 
                    <.div(^.style := s"padding: 5px 1em; background: $dc",
                        <.label(^.style := "color: white", domain.title),
                    ),
                    
                    for q <- questions yield
                        val ans = answers.value.get(q.num).getOrElse(Answer(q.num, 50))

                        <.div(^.style := s"border-bottom: 1px solid $dc", ^.attr("id") := s"question${q.num}",
                            <.p(^.style := "margin: 1em;", q.text(animal)),
                            <.div(^.style := "text-align: center",
                                <.label(^.cls := "sd", "Strongly disagree"),
                                <.input(^.attr("type") := "range", 
                                for a <- answers.value.get(q.num) yield ^.prop("value") := a.value.toString,
                                ^.onInput ==> { (e) => for v <- e.inputValue do answers.value = answers.value.updated(q.num, ans.copy(value = v.toDouble)) }
                                ),
                                <.label(^.cls := "sa", "Strongly agree"),
                            ),

                        <.div(^.style := "text-align: center", {
                            footerSelectors(q.num).value match {
                                case FooterSelection.None => Seq()
                                case FooterSelection.Confidence => 
                                    confidenceSlider(ans.confidence) { c => answers.value = answers.value.updated(q.num, ans.copy(confidence = c)) }

                            }

                        }),

                        // Mid-space controls

                        <.p(^.style := "margin: 1em;", " "),

                        // Footer controls
                        <.div(^.cls := questionFooterStyle,
                            confidenceButton(answers.value(q.num).confidence, footerSelectors(q.num)),

                            if q.num < maxQNum then <.div(^.style := "text-align: right; margin: 1em;",
                                <.button(^.cls := (button), "Next ↓", ^.onClick --> scrollQIntoView(q.num + 1))
                            ) else Seq()                 
                        )

                        
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
    import svg.*

    val gap = 10
    val boxW = 500
    val boxH = 100
    val circleR = 140

    val width = 2 * boxW + gap // Width of the stack of rectangles
    val height = 3 * boxH + 2 * gap // Height of the stack of rectangles
    val centreX = width / 2 // centre of the stack of rectangles
    val centreY = height / 2 // centre of the stack of rectangles

    val svgTop = Math.min(0, centreY - circleR)
    val svgHeight = Math.max(height, 2 * circleR)

    // The central box fits into the central circle
    val centreH = boxH 
    val centreW = (Math.sqrt(Math.pow(circleR, 2) - Math.pow(centreH / 2, 2)) * 2).toInt

    def emptyContent = html.<.p("")
    def emptyCol = "gainsboro"

    /** Aligns boxes on top of each other. */
    def stackBoxes(data: Seq[(Domain, (String, VHtmlContent))], alignLeft:Boolean) = {
        (for 
            ((domain, (col, content)), index) <- data.zipWithIndex            
        yield Seq(
            sparkbox(col, boxW, boxH)(
                ^.attr("x") := (if alignLeft then 0 else boxW + gap), 
                ^.attr("y") := (index * (boxH + gap)),
            ),
            foreignObject(
                ^.attr("x") := (if alignLeft then 0 else boxW + gap + boxH), 
                ^.attr("y") := (index * (boxH + gap)), 
                ^.attr("width") := boxW - boxH, ^.attr("height") := boxH, content),
        )).flatten
    }

    def stackMask(alignLeft:Boolean) = {
        for 
            index <- 0 to 3
        yield 
            sparkbox("white", boxW, boxH)(
                ^.attr("x") := (if alignLeft then 0 else boxW + gap), 
                ^.attr("y") := (index * (boxH + gap)),
            )
    }

    def mentalDomainCircle =
        val (mCol, mCont) = data.get(Domain.Mental).getOrElse((emptyCol, emptyContent)) 
        Seq( 
            circle(^.attr("fill") := mCol, ^.attr("cx") := centreX, ^.attr("cy") := centreY, ^.attr("r") := circleR),
            foreignObject(^.attr("x") := centreX - centreW/2, ^.attr("y") := centreY - centreH/2, ^.attr("width") := centreW, ^.attr("height") := centreH, mCont),
        )

    /** Masks the SVG, so that the gaps between the boxes let the background show through, rather than white */
    def mask = 
        SVG("mask")(
            ^.attr("id") := "logo-mask",
            stackMask(true),
            stackMask(false),
            circle(^.attr("fill") := "white", ^.attr("cx") := centreX, ^.attr("cy") := centreY, ^.attr("r") := circleR, ^.attr("stroke") := "black", ^.attr("stroke-width") := gap),
        )

    svg(^.attr("viewBox") := s"0 $svgTop $width $svgHeight",
        mask,
        g(^.attr("mask") := "url(#logo-mask)",
            stackBoxes(
                for d <- Seq(Domain.Nutrition, Domain.Environment, Domain.Health) yield d -> data.getOrElse(d, (emptyCol, emptyContent)),
                false
            ),
            stackBoxes(
                for d <- Seq(Domain.InteractionsEnvironment, Domain.InteractionsSocial, Domain.InteractionsHuman) yield d -> data.getOrElse(d, (emptyCol, emptyContent)),
                true
            ),
            mentalDomainCircle
        )
    )