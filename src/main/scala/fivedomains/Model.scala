package fivedomains

import com.wbillingsley.veautiful.PushVariable

import scala.collection.mutable

enum DisplayStyle:
    case Curls
    case Herringbone
    case Flowers
    case Circles
    case Plaid

type AnimalId = Int
case class Animal(id:AnimalId, name:String, display:DisplayStyle = DisplayStyle.Curls)

enum Domain:
    case Nutrition
    case Environment
    case Health
    case Behaviour
    case Mental

case class Question(num:Int, domain:Domain, text:Animal => String)

type QuestionSection = (String, Domain)

val allQuestions:Seq[(QuestionSection, Seq[Question])] = Seq(
    ("Nutrition", Domain.Nutrition) -> Seq(
        Question(1, Domain.Nutrition, { (a:Animal) => s"${a.name} drinks enough clean water." }),
        Question(2, Domain.Nutrition, { (a:Animal) => s"${a.name} eats nutritious, palatable and sufficient food." }),
        Question(3, Domain.Nutrition, { (a:Animal) => s"${a.name} is offered food in ways that are varied and interesting for their species." }),    
    ),
    ("Environment", Domain.Environment) -> Seq(
        Question(4, Domain.Environment, { (a:Animal) => s"${a.name} can avoid unpleasant lighting levels, noises, vibrations and odours." }),
        Question(5, Domain.Environment, { (a:Animal) => s"${a.name} can choose to occupy areas that are clean and promote physical and thermal comfort." }),
        Question(6, Domain.Environment, { (a:Animal) => s"${a.name} lives in an environment that allows them to be calm, to rest and to sleep." }),    
    ),
    ("Health", Domain.Health) -> Seq(
        Question(7, Domain.Health, { (a:Animal) => s"${a.name} is free from injury and signs of disease" }),
        Question(8, Domain.Health, { (a:Animal) => s"${a.name} has a healthy bodyweight and an appropriate level of fitness" }),
        Question(9, Domain.Health, { (a:Animal) => s"${a.name} shows no signs of acute or chronic pain, or distress." }),    
    ),
    ("Interactions with the environment", Domain.Behaviour) -> Seq(
        Question(10, Domain.Behaviour, { (a:Animal) => s"${a.name} lives in an interesting and stimulating habitat" }),
        Question(11, Domain.Behaviour, { (a:Animal) => s"${a.name} has enough room to move around freely" }),
        Question(12, Domain.Behaviour, { (a:Animal) => s"${a.name} can choose to exercise and explore freely." }),    
    ),
    ("Social interactions", Domain.Nutrition) -> Seq(
        Question(13, Domain.Behaviour, { (a:Animal) => s"${a.name} can choose how long they spend interacting with their own species" }),
        Question(14, Domain.Behaviour, { (a:Animal) => s"${a.name} has the space to avoid conflicts with other non-human animals" }),
        Question(15, Domain.Behaviour, { (a:Animal) => s"${a.name} can choose to interact appropriately with non-human animals of other species." }),    
    ),
    ("Interactions with humans", Domain.Nutrition) -> Seq(
        Question(16, Domain.Behaviour, { (a:Animal) => s"${a.name} has healthy bonds with their carer(s)" }),
        Question(17, Domain.Behaviour, { (a:Animal) => s"${a.name} is calm in the presence of humans" }),
        Question(18, Domain.Behaviour, { (a:Animal) => s"${a.name} engages confidently and safely when interacting with familiar humans" }),    
    ),
)

val flattenedQs = for 
    (_, qs) <- allQuestions
    q <- qs
yield q

/** THe confidence a user has in their answer to a question */
enum Confidence(val abbreviation:String):
    case VeryLow extends Confidence("VL")
    case Low extends Confidence("L")
    case Medium extends Confidence("M")
    case High extends Confidence("H")
    case VeryHigh extends Confidence("VH")

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

def nextAnimalId = (0 :: animals.toList.map(_.id)).max + 1

val assessments = mutable.Buffer.empty[Assessment]

def surveysFor(a:Animal) = assessments.toSeq.filter(_.animal == a.id)
