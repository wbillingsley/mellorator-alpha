package fivedomains

import com.wbillingsley.veautiful.html.*
import com.wbillingsley.veautiful.templates.*
import com.wbillingsley.veautiful.PathDSL


enum AppRoute:
    case Front
    case AddAnimal
    case Animal(id:AnimalId)

object Router extends HistoryRouter[AppRoute] {

    var route =AppRoute.Front

    override def path(r:AppRoute) = r match 
        case AppRoute.Front => "#/"
        case AppRoute.AddAnimal => "#/addanimal"
        case AppRoute.Animal(id) => s"#/animals/$id"

    override def render = this.route match 
        case AppRoute.Front => 
            frontPage
        case AppRoute.AddAnimal =>
            AnimalForm()
        case AppRoute.Animal(id) => 
            <.p("Write the animal page") 
    
    override def routeFromLocation() = PathDSL.hashPathList() match {
        case "addanimal" :: Nil => AppRoute.AddAnimal
        case _ => AppRoute.Front
    }

}