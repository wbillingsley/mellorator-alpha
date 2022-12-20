package fivedomains

import com.wbillingsley.veautiful.*
import html.{VHtmlContent, Styling, DHtmlComponent}

val nutritionCol = "#e6ca84"
val environmentCol = "#5F6B6A"
val healthCol = "#FF5F58"
val behaviourCol = "#354D51"
val mentalCol = "#4D5D38"

val smallFont = "15pt"

val button = Styling(
    s"font-size: $smallFont; margin-left: 0.5em; border-radius: 0.25em; padding: 1em; border: none;"
).modifiedBy(
    ".active" -> "font-weight: bold; background: none; color: black;",
    ".enabled" -> "background-color: aliceblue; color: cornflowerblue;"
).register()

val primary = Styling(
   ""
).register()

val card = Styling(
    "box-shadow: 0 3px 3px #aaa; margin: 20px; padding: 20px; font-family: sans-serif;"
).register()

def fourGrid(a:VHtmlContent, b:VHtmlContent, c:VHtmlContent, d:VHtmlContent):VHtmlContent = 
    import html.{<, ^}
    <.div(^.attr("style") := "display: grid; grid-template-columns: 1fr 1fr; gap: 10px;",
        a, b, c, d
    )

def sparkbox(color:String, width:Int, height:Int) = 
    import svg.*
    rect(^.attr("width") := width, ^.attr("height") := height, ^.attr("fill") := color)




def logo = 
    val gap = 10
    val boxW = 400
    val boxH = 100
    val circleR = 140

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
    import html.^
    svg(^.attr("viewBox") := s"0 ${boxH - circleR} ${2 * boxW} ${2 * circleR}",
        mask,
        g(^.attr("mask") := "url(#logo-mask)",
            sparkbox(nutritionCol, boxW - gap/2, boxH - gap/2)(^.attr("x") := 0, ^.attr("y") := 0),
            sparkbox(environmentCol, boxW - gap/2, boxH - gap/2)(^.attr("x") := boxW + gap/2, ^.attr("y") := 0),
            sparkbox(healthCol, boxW - gap/2, boxH - gap/2)(^.attr("x") := 0, ^.attr("y") := boxH + gap/2),
            sparkbox(behaviourCol, boxW - gap/2, boxH - gap/2)(^.attr("x") := boxW + gap/2, ^.attr("y") := boxH+gap/2),
            path(
                ^.attr("d") := "M 40 140 c 30 0 60 -80 90 -80 c 30 0 60 60 90 60 c 40 0 80 -60 120 -60 c 40 0 80 60 120 60 c 40 0 60 -60 90 -60 c 40 0 80 60 120 60 c 30 0 60 -80 90 -80",
                ^.attr("style") := "fill: none; stroke: white; stroke-width: 5;"
            ),
            circle(^.attr("fill") := mentalCol, ^.attr("cx") := boxW, ^.attr("cy") := boxH, ^.attr("r") := circleR),
        )
        //text(^.attr("x") := 300, ^.attr("y") := 100, "AWAT")
    )


/**
  * The header that goes at the top of the front page
  */
def frontHeader = 
    import html.*
    <.div(
        ^.cls := Styling(
            "margin: -10px -10px 3em; position: relative; top: 0; height: 420px; box-shadow: 0 3px 3px #aaa;"
        ).modifiedBy(
            " .bgimage" -> """
                |height: 420px;
                |opacity: 0.5;
                |position: absolute; height: 100%; width: 100%; top: 0;
                |background-image: url("images/grass oil.jpeg");
                |background-position: center;
                |background-blend-mode: soft-light;
                |background-size: cover;
                |""".stripMargin,
            " .title-block" -> "text-align: center; position: absolute; bottom: 1em; font-family: sans-serif; font-size: 24pt; width: 100%;",
            " .five-domains-logo" -> "width: 180px;"
        ).register().className,
        <.div(^.cls := "bgimage"),
        <.div(^.cls := "title-block",
            <.div(
                logo(^.cls := "five-domains-logo")
            ),
            <.div(^.cls := "title",
                "Animal Welfare Assessment Toolkit"
            )
        )        
    )

def leftBlockHeader(backurl:String, title:String, block:VHtmlContent, decoration:VHtmlContent = html.<.div()) = 
    import html.{<, ^}
    <.div(
        ^.cls := Styling(
                "margin: -10px -10px 3em; position: relative; top: 0; height: 240px; padding: 20px; box-shadow: 0 3px 3px #aaa; font-family: sans-serif;"
            ).modifiedBy(
                " .bgimage" -> """
                  |opacity: 0.5;
                  |position: absolute; height: 100%; width: 100%; top: 0; left: 0;
                  |background-image: url("images/grass oil.jpeg");
                  |background-position: center;
                  |background-size: cover;
                  |""".stripMargin,
                " .links a" -> "text-decoration: none; font-size: 16pt; color: black;",
                " .title-block" -> "font-size: 32pt; width: 100%; color: white; margin-top: 1em;",
                " .block" -> "position: absolute; bottom: 0;",
            ).register().className,

            <.div(^.cls := "bgimage"),

            <.div(^.cls := "links", <.a(^.href := backurl, "← Back")),
            <.div(^.cls := "title-block", title),
            <.div(^.cls := "block", block),
            <.div(^.cls := "decoration", decoration),
    )