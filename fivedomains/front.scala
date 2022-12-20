package fivedomains

import com.wbillingsley.veautiful.*
import html.{Styling, VHtmlContent, DHtmlComponent}

object AnimalList extends DHtmlComponent {
    import html.{<, ^}

    enum OrderBy:
        case LeastRecentlyUpdated
        case Alphabetical

    val listMode = stateVariable(OrderBy.Alphabetical)

    def sortedAnimals = listMode.value match {
        case OrderBy.Alphabetical => 
            animals.sortBy(_.name)
        case OrderBy.LeastRecentlyUpdated => 
            animals.sortBy { a => assessments.filter(_.animal == a.id).sortBy(_.time).map(_.time).lastOption.getOrElse(0d) }

    }

    def switcher = <.div(        

        ^.cls := Styling(
            "text-align: center; margin: 3em;"
        ).modifiedBy().register().className,


        listMode.value match {
            case OrderBy.Alphabetical => 
                Seq(
                    <.button(^.cls &= Seq(button, "active"), ^.attr("disabled") := "disabled", "Alphabetical"),
                    <.button(^.cls &= Seq(button, "enabled"), "Least recently surveyed", ^.onClick --> listMode.receive(OrderBy.LeastRecentlyUpdated)),
                )
            case OrderBy.LeastRecentlyUpdated => 
                Seq(
                    <.button(^.cls &= Seq(button, "enabled"), "Alphabetical", ^.onClick --> listMode.receive(OrderBy.Alphabetical)),
                    <.button(^.cls &= Seq(button, "active"), ^.attr("disabled") := "disabled", "Least recently surveyed"),
                )
        }
        
    )

    def render = <.div(
        switcher,


    )

}

def frontPage = html.<.div(
    frontHeader,
    if animals.isEmpty then emptyCard else AnimalList
)