package fivedomains.animals

import com.wbillingsley.veautiful.*
import html.{Styling, VHtmlContent, DHtmlComponent, <, ^, EventMethods}

import fivedomains.{given, *}
import model.*

class AnimalForm() extends DHtmlComponent {

    val animal = stateVariable(Animal(DataStore.nextAnimalId, "Almeira", Species.Horse))

    def add():Unit = 
        DataStore.addAnimal(animal.value)
        Router.routeTo(AppRoute.Front)

    def render = <.div(
        leftBlockHeader(
            Router.path(AppRoute.Front),
            "Add an animal",
            <.label(^.cls := (animalName), animal.value.name)
        ),

        emptyAnimalsNotice,

        <.div(
            ^.cls := (card, backgrounds(animal.value.display)),
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
                        <.label(^.attr("for") := displayStyle.toString, ^.cls := (patterns(displayStyle)), ^.style := "display: inline-block; width: 40px; height: 40px; vertical-align: middle;"),
                    )
            ),
            <.div(^.style := "text-align: right;",
                <.button(^.cls := (button, noticeButton), "Add", ^.onClick --> add())
            )
        )
    )
}


def emptyAnimalsNotice = if DataStore.animals.nonEmpty then <.div() else <.div(^.cls := (notice),
    markdown.div(
        """|### Let's add your first animal
           |
           |The animal welfare assessment works for most species, so we just need to give your animal a name.
           |
           |You can also pick a background so that if you have several animals you are monitoring, it's visually easier to distinguish their cards.
           |""".stripMargin
    )
)

def addAnimalPage = <.div(
    animals.AnimalForm()
)