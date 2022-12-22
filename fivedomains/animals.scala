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
        animals.append(animal.value)
        Router.routeTo(AppRoute.Front)

    def render = <.div(
        leftBlockHeader(
            Router.path(AppRoute.Front),
            "Add an animal",
            <.h2(animal.value.name)
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
     <.div(
            ^.cls &= Seq(card, backgrounds(animal.display)),
            <.div(
                <.label(^.cls &= Seq(animalName), animal.name)
            ),
            <.div(
                
            )
     )