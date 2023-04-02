//> using repository "sonatype:snapshots"
//> using dep "com.wbillingsley::doctacular::0.3.0"

package fivedomains

import com.wbillingsley.veautiful.html.*
import com.wbillingsley.veautiful.doctacular.*
import org.scalajs.dom

import scalajs.js
import scala.scalajs.js.annotation._

import Installers.installMarked.installMarked

val root = mount("#render-here", <.p("Loading..."))
given marked:Markup = root.installMarked("4.3.0")

given styleSuite:StyleSuite = StyleSuite()

@main def main = {
  styleSuite.install()
  root.render(Router)
}
