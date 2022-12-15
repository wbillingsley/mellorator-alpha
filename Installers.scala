import com.wbillingsley.veautiful.*
import html.*
import org.scalajs.dom
import scalajs.js


object Installers {

  def _install(url:String)(onLoad: () => Unit) = {
    dom.console.log(s"Installing $url")

    val head = dom.document.head
    val script = dom.document.createElement("script")
    script.setAttribute("src", url)

    val pv = PushVariable[Boolean](false)(_ => ())
    script.addEventListener("load", (_) => 
      dom.console.log("Load event!")
      pv.value = true
    )
    head.append(script)
    pv.dynamic    
  }

  def installAndRefresh(url:String):Boolean = 
    val dv = _install(url)(() => ())
    dv.subscribe { _ => if dv.immediateValue then site.router.routeTo(site.router.route) }

  lazy val marked = {
    val markedJS = installAndRefresh("https://cdnjs.cloudflare.com/ajax/libs/marked/4.2.4/marked.min.js") 

    Markup { (s:String) => 
      if js.typeOf(js.Dynamic.global.marked) == "undefined" then "Loading marked.js" else js.Dynamic.global.marked.parse(s).asInstanceOf[String] 
    }
  }

}