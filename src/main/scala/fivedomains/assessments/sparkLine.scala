package fivedomains.assessments

import com.wbillingsley.veautiful.*
import html.{Styling, VHtmlContent, DHtmlComponent, EventMethods}

import fivedomains.*
import model.*

def sparkLine(data: Seq[(Double, Double)], range:(Double, Double), width:Int, height:Int, colour:String) = {
    import svg.* 

    val (min, max) = range
    val startX = data.map(_._1).min
    val endX = data.map(_._1).max
    val dataWidth = endX - startX

    val xScale = if dataWidth > 0 then width / dataWidth else 1
    val yScale = height.toDouble / (max - min)

    val points = data.map { case (x, y) => (xScale * (x - startX), height - (yScale * (y - min))) }

    val path = points.map { case (x, y) => s"$x,$y" }.mkString(" M ", " L ", "")

    svg(^.attr.style := "margin: 25px;",
        ^.attr("width") := width, ^.attr("height") := height,
        <.path(^.attr("d") := path, ^.attr("stroke") := colour, ^.attr("stroke-width") := 7, ^.attr("fill") := "none")
    )
}
