package fivedomains

import com.wbillingsley.veautiful.*
import html.{Styling, VHtmlContent, DHtmlComponent, <, ^}

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
    <.a(^.cls &= Seq(button), ^.href := Router.path(AppRoute.AddAnimal), "Add an animal")
)

def animalCard(a:Animal) = <.div(
    ^.cls &= Seq(
        card
    ),


)

class AnimalForm() extends DHtmlComponent {

    val animal = stateVariable(Animal(nextAnimalId, "horse", "Almeira"))

    def render = <.div(
        leftBlockHeader(
            Router.path(AppRoute.Front),
            "Add an animal",
            <.h2(animal.value.name)
        ),



    )

}
