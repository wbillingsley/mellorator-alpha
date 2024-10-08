package fivedomains

import com.wbillingsley.veautiful.html.*
import com.wbillingsley.veautiful.templates.*

import model.*
import java.util.UUID


enum AppRoute:
    case Front
    case Settings
    case AddAnimal
    case Animal(id:AnimalId)
    case Assess(id:AnimalId)

object Router extends HistoryRouter[AppRoute] {

    var route =AppRoute.Front

    override def path(r:AppRoute) = r match 
        case AppRoute.Front => "#/"
        case AppRoute.Settings => "#/settings"
        case AppRoute.AddAnimal => "#/addanimal"
        case AppRoute.Animal(id) => s"#/animals/$id"
        case AppRoute.Assess(id) => s"#/assess/$id"

    override def render = this.route match 
        case AppRoute.Front => 
            <.div(^.cls := (top), frontPage)
        case AppRoute.Settings => 
            <.div(^.cls := (top), settingsPage)
        case AppRoute.AddAnimal =>
            <.div(^.cls := (top), animals.addAnimalPage)
        case AppRoute.Animal(id) => 
            <.div(^.cls := (top), animals.animalDetailsPage(id))
        case AppRoute.Assess(id) => 
            <.div(^.cls := (top), assessments.assessmentPage(id))
    
    override def routeFromLocation() = PathDSL.hashPathList() match {
        case "settings" :: Nil => AppRoute.Settings
        case "addanimal" :: Nil => AppRoute.AddAnimal
        case "animals" :: AnimalId(a) :: Nil if DataStore.animalMap.contains(a) => AppRoute.Animal(a)
        case "assess" :: AnimalId(a) :: Nil if DataStore.animalMap.contains(a) => AppRoute.Assess(a)
        case _ => AppRoute.Front
    }

}