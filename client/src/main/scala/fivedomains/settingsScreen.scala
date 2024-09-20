package fivedomains

import com.wbillingsley.veautiful.*
import html.{VHtmlContent, Styling, DHtmlComponent, Animator}
import fivedomains.testdata.addPickles

object SettingsScreen extends DHtmlComponent {
    import html.{<, ^}

    def render = <.div(
        "Settings"
    )

}


case class ResetFirstTimeNotice() extends DHtmlComponent {

    val enabled = stateVariable(false)

    def clearFlag() = 
        DataStore.clearAcceptSensitiveTopics() 

    override def render = {
        import html.{<, ^}
        <.div(
            <.p(
                """|The first use notice shows a sensitive topics warning and other disclaimers. This resets the flag about whether it should appear
                   |""".stripMargin

            ),

            <.input(^.attr("type") := "checkbox", ^.prop.checked := enabled.value, ^.onChange --> { enabled.value = !enabled.value}),
            <.label("Tick to unlock"),
            <.button(^.cls := (button, noticeButton), ^.prop.disabled := !enabled.value, "Reset first use notice", ^.onClick --> clearFlag())
        )
    }

}

case class ResetData() extends DHtmlComponent {

    val enabled = stateVariable(false)

    def reset() = 
        DataStore.clearAll()
        addPickles()
        enabled.value = false

    override def render = {
        import html.{<, ^}
        <.div(
            <.p(
                """|The app currently stores its data only in the browser's local storage. 
                   |If the data is reset, it cannot be recovered.
                   |""".stripMargin

            ),

            <.input(^.attr("type") := "checkbox", ^.prop.checked := enabled.value, ^.onChange --> { enabled.value = !enabled.value}),
            <.label("Tick to unlock"),
            <.button(^.cls := (button, dangerButton), ^.prop.disabled := !enabled.value, "Reset all data", ^.onClick --> reset())
        )
    }

}


def settingsPage = 
    import html.* 
    <.div(
        leftBlockHeader(
            Router.path(AppRoute.Front),
            "Settings",
            <.p()
        ),

        <.div(^.style := "margin: 1em;",


        <.h2("First use"),
        ResetFirstTimeNotice(),

        <.h2("Data"),
        ResetData()
        
        )

        


    )
