//> using repository "sonatype:snapshots"

package fivedomains

import $dep.`com.wbillingsley::doctacular::0.3-M4`

import com.wbillingsley.veautiful.html.*
import com.wbillingsley.veautiful.templates.DeckBuilder
import com.wbillingsley.veautiful.doctacular.*
import org.scalajs.dom

import scalajs.js
import scala.scalajs.js.annotation._

val root = mount("#render-here", <.p("Loading..."))
given marked:Markup = Installers.marked

given styleSuite:StyleSuite = StyleSuite()

@main def main = {
  styleSuite.install()
  root.render(Router)
}
