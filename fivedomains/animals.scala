package fivedomains

import com.wbillingsley.veautiful.*
import html.{Styling, VHtmlContent, DHtmlComponent, <, ^, EventMethods}

def emptyCard = <.div(^.cls &= Seq(
        Styling("""|border: 1px dashed #aaa;
                   |border-radius: 0.25em;
                   |text-align: center;
                   |""".stripMargin
        ).modifiedBy(
            " .title" -> 
                """|-webkit-text-stroke-width: 1px;
                   |-webkit-text-stroke-color: #aaa;
                   |""".stripMargin
        ).register(),
        card
    ),
    <.h3(^.cls := "title", "You haven't registered any animals"),
)

def animalCard(a:Animal) = <.div(
    ^.cls &= Seq(
        card
    ),


)

class AnimalForm() extends DHtmlComponent {

    val animal = stateVariable(Animal(nextAnimalId, "Almeira"))

    def add():Unit = 
        addAnimal(animal.value)
        Router.routeTo(AppRoute.Front)

    def render = <.div(
        leftBlockHeader(
            Router.path(AppRoute.Front),
            "Add an animal",
            <.label(^.cls &= Seq(animalName), animal.value.name)
        ),

        emptyAnimalsNotice,

        <.div(
            ^.cls &= Seq(card, backgrounds(animal.value.display)),
            <.div(
                <.label("Name"),
                <.input(^.style := s"margin-left: 0.25em; font-size: $largeFont;",
                    ^.prop("value") := animal.value.name, 
                    ^.on("input") ==> { e => for n <- e.inputValue do animal.value = animal.value.copy(name = n) }
                )
            ),
            <.ul(^.style := "list-item: none; padding: 0;",
                for displayStyle <- DisplayStyle.values yield
                    <.li(^.style := "display: inline-block; margin: 1em;",
                        <.input(^.attr("id") := displayStyle.toString, ^.attr("type") := "radio", 
                            ^.prop("checked") := (if displayStyle == animal.value.display then "true" else ""), 
                            ^.onClick --> (animal.value = animal.value.copy(display=displayStyle))
                        ),
                        <.label(^.attr("for") := displayStyle.toString, ^.cls &= Seq(patterns(displayStyle)), ^.style := "display: inline-block; width: 40px; height: 40px; vertical-align: middle;"),
                    )
            ),
            <.div(^.style := "text-align: right;",
                <.button(^.cls &= Seq(button, noticeButton), "Add", ^.onClick --> add())
            )
        )
    )
}

def emptyAnimalsNotice = if animals.nonEmpty then <.div() else <.div(^.cls &= Seq(notice),
        <.h3("Let's add your first animal"),
        <.p("The animal welfare assessment works for most species, so we just need to give your animal a name."),
        <.p("You can also pick a background so that if you have several animals you are monitoring, it's visually easier to distinguish their cards."),
    )

def addAnimalPage = <.div(
    AnimalForm()
)

def summaryCard(animal:Animal) = 
    val surveys = surveysFor(animal).sortBy(_.time)

     <.div(
            ^.cls &= Seq(card, backgrounds(animal.display)),
            <.div(
                <.label(^.cls &= Seq(animalName), animal.name)
            ),
            <.div(
                if surveys.isEmpty then 
                    <.div("Never assessed") else 
                    <.div(
                        <.div(s"${surveys.length} assessments recorded"),
                        {
                            val dt = (new scalajs.js.Date().valueOf - surveys.last.time) / (1000 * 60 * 60 * 24)
                            <.div(s"Last assessed ${dt.toInt} days ago")
                        }
                    )
            ),
            <.div(
                surveys.lastOption match {
                    case Some(s) =>
                        def score(d:Domain):(String, VHtmlContent) = 
                            val avg = s.average(d)
                            val col = scoreColor(avg)

                            col -> <.div(^.style := "text-align: center", 
                                <.label(^.cls &= Seq(fiveboxtext), scoreText(avg))
                            )


                        fiveBox((for d <- Domain.values yield d -> score(d)).toMap)(^.style := "width: 50%;")
                    case None => 
                        fiveBox(Map.empty)(^.style := "width: 50%;")
                }
                
            ),
            <.div(^.style := "text-align: right;",
                <.button(^.cls &= Seq(button, primary), "Assess", ^.onClick --> Router.routeTo(AppRoute.Assess(animal.id)))
            )            
     )


def animalDetailsPage(aId:AnimalId) = 
    val a = animalMap(aId)
    def recentSurvey = surveysFor(a).lastOption

    <.div(
        leftBlockHeader(
            Router.path(AppRoute.Front),
            "Animal details",
            <.label(^.cls &= Seq(animalName), a.name)
        ),

        for s <- recentSurvey yield <.div(^.style := "margin: 1.5em;",
            <.h3(s"Last assessed ${new scalajs.js.Date(s.time).toLocaleDateString}"),
            <.div(^.style := "text-align: center;",
                fiveBox(
                    data=(
                        for d <- Domain.values yield 
                            d -> (scoreColor(s.average(d)), <.label(^.cls &= Seq(fiveboxtext), d.toString))
                    ).toMap
                )(^.style := "width: 50%;")
            )
        ),

        <.div(^.style := s"padding: 5px 1em; background: ${domainColour(Domain.Mental)}",
                        <.label(^.style := "color: white", Domain.Mental.toString),
        ),

        <.div(^.style := "margin: 1.5em;",
            <.p("...trend of last surveys...?")
        ),

        for d <- Seq(Domain.Nutrition, Domain.Environment, Domain.Health, Domain.Behaviour) yield <.div(
            <.div(^.style := s"padding: 5px 1em; background: ${domainColour(d)}",
                            <.label(^.style := "color: white", d.toString),
            ),

            <.div(^.style := "margin: 1.5em;",
                <.p("...trend of last surveys...?")
            ),
        )




    )