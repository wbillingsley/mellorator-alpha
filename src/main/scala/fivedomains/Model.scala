package fivedomains

import com.wbillingsley.veautiful.PushVariable

import scala.collection.mutable

/** The display style is purely decorative, as placeholder because at the moment we don't have iconography to make animals disinct */
enum DisplayStyle:
    case Curls
    case Herringbone
    case Flowers
    case Circles
    case Plaid

/** Used internally to track animals etc */
type AnimalId = Int

case class Animal(id:AnimalId, name:String, display:DisplayStyle = DisplayStyle.Curls)

enum Domain(val title:String, val color:String):
    case Nutrition extends Domain("Nutrition", nutritionCol)
    case Environment extends Domain("Environment", environmentCol)
    case Health extends Domain("Health", healthCol)
    case InteractionsEnvironment extends Domain("Interactions with the environment", behaviourCol)
    case InteractionsSocial extends Domain("Social interactions", behaviourCol)
    case InteractionsHuman extends Domain("Interactions with humans", behaviourCol)
    case Mental extends Domain("Mental wellbeing", mentalCol)

case class Question(num:Int, domain:Domain, text:Animal => String)

type QuestionSection = Domain

val allQuestions:Seq[(QuestionSection, Seq[Question])] = Seq(
    Domain.Nutrition -> Seq(
        Question(1, Domain.Nutrition, { (a:Animal) => s"${a.name} drinks enough clean water." }),
        Question(2, Domain.Nutrition, { (a:Animal) => s"${a.name} eats nutritious, palatable and sufficient food." }),
        Question(3, Domain.Nutrition, { (a:Animal) => s"${a.name} is offered food in ways that are varied and interesting for their species." }),    
    ),
    Domain.Environment -> Seq(
        Question(4, Domain.Environment, { (a:Animal) => s"${a.name} can avoid unpleasant lighting levels, noises, vibrations and odours." }),
        Question(5, Domain.Environment, { (a:Animal) => s"${a.name} can choose to occupy areas that are clean and promote physical and thermal comfort." }),
        Question(6, Domain.Environment, { (a:Animal) => s"${a.name} lives in an environment that allows them to be calm, to rest and to sleep." }),    
    ),
    Domain.Health -> Seq(
        Question(7, Domain.Health, { (a:Animal) => s"${a.name} is free from injury and signs of disease" }),
        Question(8, Domain.Health, { (a:Animal) => s"${a.name} has a healthy bodyweight and an appropriate level of fitness" }),
        Question(9, Domain.Health, { (a:Animal) => s"${a.name} shows no signs of acute or chronic pain, or distress." }),    
    ),
    Domain.InteractionsEnvironment -> Seq(
        Question(10, Domain.InteractionsEnvironment, { (a:Animal) => s"${a.name} lives in an interesting and stimulating habitat" }),
        Question(11, Domain.InteractionsEnvironment, { (a:Animal) => s"${a.name} has enough room to move around freely" }),
        Question(12, Domain.InteractionsEnvironment, { (a:Animal) => s"${a.name} can choose to exercise and explore freely." }),    
    ),
    Domain.InteractionsSocial -> Seq(
        Question(13, Domain.InteractionsSocial, { (a:Animal) => s"${a.name} can choose how long they spend interacting with their own species" }),
        Question(14, Domain.InteractionsSocial, { (a:Animal) => s"${a.name} has the space to avoid conflicts with other non-human animals" }),
        Question(15, Domain.InteractionsSocial, { (a:Animal) => s"${a.name} can choose to interact appropriately with non-human animals of other species." }),    
    ),
    Domain.InteractionsHuman -> Seq(
        Question(16, Domain.InteractionsHuman, { (a:Animal) => s"${a.name} has healthy bonds with their carer(s)" }),
        Question(17, Domain.InteractionsHuman, { (a:Animal) => s"${a.name} is calm in the presence of humans" }),
        Question(18, Domain.InteractionsHuman, { (a:Animal) => s"${a.name} engages confidently and safely when interacting with familiar humans" }),    
    ),
)

val flattenedQs = for 
    (_, qs) <- allQuestions
    q <- qs
yield q


case class Answer(q:Int, value:Double, confidence:Confidence = Confidence.Medium)

case class Assessment(animal:AnimalId, time:Double, answers:Map[Int, Answer]) {

    /** Average score in a given domain */
    def average(domain:Domain):Double = domain match {
        case Domain.Mental => 
            answers.values.map(_.value).sum / answers.size
        case d => 
            val filtered = answers.values.filter { (a) => flattenedQs.find(_.num == a.q).map(_.domain).contains(d) }
            filtered.map(_.value).sum / filtered.size
    }

}

def addAnimal(a:Animal):Unit =
    animalMap(a.id) = a

def nextAnimalId = (0 :: DataStore.animals.toList.map(_.id)).max + 1

