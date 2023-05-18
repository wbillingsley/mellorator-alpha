package fivedomains.model

import fivedomains.*

case class Question(num:Int, domain:Domain, text:Animal => String, kind:QuestionType = QuestionType.StronglyAgreeSlider) {

    /**
     * The default (unchanged) answer to show for this question when a form is first presented
     */
    def defaultAnswer:Answer = kind match {
        case QuestionType.StronglyAgreeSlider => Answer(num,AnswerValue.Numeric(50), Confidence.Medium)
        case QuestionType.RatingPicker => Answer(num, AnswerValue.Rated(Rating.Neutral), Confidence.Medium)
    }

}

type QuestionSection = Domain

enum QuestionType derives upickle.default.ReadWriter:
    case StronglyAgreeSlider
    case RatingPicker

val allQuestions:Seq[(QuestionSection, Seq[Question])] = Seq(
    Domain.Mental -> Seq(
        Question(0, Domain.Mental, { (a:Animal) => s"Overall, how would you rate ${a.name}'s wellbeing?" }, QuestionType.RatingPicker),
    ),
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

lazy val questionMap = allQuestions.flatMap { case (s, qs) => qs.map(q => q.num -> q) }.toMap

val flattenedQs = for 
    (_, qs) <- allQuestions
    q <- qs
yield q


enum AnswerValue derives upickle.default.ReadWriter:
    case Numeric(value:Double)
    case Rated(value:Rating)

    def asDouble = this match {
        case Numeric(v) => v
        case Rated(r) => r.value
    }


case class Answer(q:Int, value:AnswerValue, confidence:Confidence = Confidence.Medium)