package fivedomains.assessments

import com.wbillingsley.veautiful.*
import html.{Styling, VHtmlContent, DHtmlComponent, EventMethods}

import fivedomains.{given, *}
import model.*

def nextRandomChar():Char = (scala.util.Random.nextInt(0xc7ff) + 0x1000).toChar

def randomHtmlId() = Seq.fill(10)(nextRandomChar()).mkString

val alignLeftStyle = Styling("text-align: left;").register()
val alignRightStyle = Styling("text-align: right;").register()
val alignCentreStyle = Styling("text-align: center;").register()

/**
  * The "seven box" diagram contains the six manually surveyed domains (as rectangles), with the 
  * mental domain superimposed as a circle in the middle.
  * 
  * This SVG diagram format is also used to generate the logo for the app.
  */
def sevenBox(data: Map[Domain, (String, VHtmlContent)]) = 
    import svg.*

    val gap = 10
    val boxW = 500
    val boxH = 100
    val circleR = 180

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
                ^.cls := (if alignLeft then alignLeftStyle else alignRightStyle),
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
            foreignObject(
                ^.attr.style := "text-anchor: middle; dominant-baseline: middle;",
                ^.attr("x") := centreX - centreW/2, ^.attr("y") := centreY - centreH/2, 
                ^.attr("width") := centreW, ^.attr("height") := centreH,
                ^.cls := alignCentreStyle, 
                mCont
            ),
        )

    
    val maskId = randomHtmlId()
    /** Masks the SVG, so that the gaps between the boxes let the background show through, rather than white */
    def mask = 
        SVG("mask")(
            ^.attr("id") := maskId,
            stackMask(true),
            stackMask(false),
            circle(^.attr("fill") := "white", ^.attr("cx") := centreX, ^.attr("cy") := centreY, ^.attr("r") := circleR, ^.attr("stroke") := "black", ^.attr("stroke-width") := gap),
        )

    svg(^.attr("viewBox") := s"0 $svgTop $width $svgHeight",
        mask,
        g(^.attr("mask") := s"url(#$maskId)",
            stackBoxes(
                for d <- Seq(Domain.Nutrition, Domain.Environment, Domain.Health) yield d -> data.getOrElse(d, (emptyCol, emptyContent)),
                true
            ),
            stackBoxes(
                for d <- Seq(Domain.InteractionsEnvironment, Domain.InteractionsSocial, Domain.InteractionsHuman) yield d -> data.getOrElse(d, (emptyCol, emptyContent)),
                false
            ),
            mentalDomainCircle
        )
    )



/** A sevenBox containing just the text of one survey */
def scoreText7(assessment:Assessment) =
    import html.* 
    def score(d:Domain):(String, VHtmlContent) = 
        val avg = assessment.average(d)
        val col = scoreColor(avg)

        col -> <.div(^.style := "text-align: center", 
            <.label(^.cls := (fiveboxtext), scoreText(avg))
        )

    sevenBox((for d <- Domain.values yield d -> score(d)).toMap)(^.style := "")

/** A sevenBox containing just the text of one survey */
def sparkTrend7(assessments:Seq[Assessment]) =
    import html.* 
    val boxW = 250
    val boxH = 80

    def score(d:Domain):(String, VHtmlContent) = 
        d.color -> <.div(sparkLine(
            for (a, i) <- assessments.zipWithIndex yield i.toDouble /*a.time*/ -> a.average(d), (0, 100), boxW, boxH, "white"
        ))

    sevenBox((for d <- Domain.values yield d -> score(d)).toMap)(^.style := "")

/** A sevenBox containing just the text of one survey */
def colouredSparkTrend7(assessments:Seq[Assessment]) =
    import html.* 
    val boxW = 280
    val boxH = 80

    def score(d:Domain):(String, VHtmlContent) =
        val color = (for last <- assessments.lastOption yield scoreColor(last.average(d))).getOrElse(d.color)
        color -> <.div(
            sparkLine(
                for (a, i) <- assessments.zipWithIndex yield i.toDouble /*a.time*/ -> a.average(d), (0, 100), boxW, boxH, "white"
            )
        )

    sevenBox((for d <- Domain.values yield d -> score(d)).toMap)(^.style := "")