package fivedomains.animals

import fivedomains.{given, *}
import com.wbillingsley.veautiful.*
import html.*

def animalDetailsPage(aId:AnimalId) = 
    val a = animalMap(aId)
    def recentSurvey = DataStore.surveysFor(a).lastOption

    <.div(
        leftBlockHeader(
            Router.path(AppRoute.Front),
            "Animal details",
            <.label(^.cls := (animalName), a.name)
        ),

        for s <- recentSurvey yield <.div(^.style := "margin: 1.5em;",
            <.h3(s"Last assessed ${new scalajs.js.Date(s.time).toLocaleDateString}"),
            <.div(^.style := "text-align: center;",
                assessments.sevenBox(
                    data=(
                        for d <- Domain.values yield 
                            d -> (scoreColor(s.average(d)), <.label(^.cls := (fiveboxtext), d.toString))
                    ).toMap
                )(^.style := "")
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