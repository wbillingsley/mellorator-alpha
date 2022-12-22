package fivedomains

import com.wbillingsley.veautiful.*
import html.{Styling, VHtmlContent, DHtmlComponent}

object AnimalList extends DHtmlComponent {
    import html.{<, ^}

    enum OrderBy:
        case LeastRecentlyUpdated
        case Alphabetical

    val listMode = stateVariable(OrderBy.LeastRecentlyUpdated)

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
                    <.button(^.cls &= Seq(button, "enabled"), "Survey due", ^.onClick --> listMode.receive(OrderBy.LeastRecentlyUpdated)),
                )
            case OrderBy.LeastRecentlyUpdated => 
                Seq(
                    <.button(^.cls &= Seq(button, "enabled"), "Alphabetical", ^.onClick --> listMode.receive(OrderBy.Alphabetical)),
                    <.button(^.cls &= Seq(button, "active"), ^.attr("disabled") := "disabled", "Survey due"),
                )
        }
        
    )

    def render = <.div(
        if animals.isEmpty then Seq(emptyCard) else Seq(
            switcher,
            <.div(
                for a <- animals yield summaryCard(a)
            ),
        ),
        <.p(^.style := "margin-top: 1em; text-align: center;",
            <.a(^.cls &= Seq(button, primary), ^.href := Router.path(AppRoute.AddAnimal), "Add an animal")
        )
    )

}

case class SensitiveTopicNotice() extends DHtmlComponent {
    import html.{<, ^}

    def render = <.div(^.cls &= Seq(notice),
        <.h3("Sensitive topics"),
        <.p("This app will help you to monitor your animals' welfare using the \"Five Domains\" model of nutrition, environment, health, behaviour and the mental domain."),
        <.p("At times, this may involve showing how an animal's welfare has declined as well as how it has improved. Some people may find it distressing to see an animal in decline."),
        <.div(^.style := "text-align: right;",
            <.input(^.attr("type") := "checkbox", ^.prop("checked") := "checked"), <.label("Don't show this again "),
            <.button(^.cls &= Seq(button, noticeButton), "Accept", ^.onClick --> acceptedSensitiveTopics.receive(true))
        )
    )

}

def frontPage = html.<.div(
    frontHeader,
    if acceptedSensitiveTopics.value then AnimalList else SensitiveTopicNotice(),
)
