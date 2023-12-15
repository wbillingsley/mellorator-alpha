package fivedomains.model

import fivedomains.*

case class Question(num:Int, domain:Domain, 
    headline:Animal => String, 
    shortExplanation:Animal => String, 
    longExplanation:Animal => String, 
    kind:QuestionType = QuestionType.StronglyAgreeSlider
) {

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


enum QuestionIdentifier:
    case Overall
    case DrinksEnough
    case EatsEnough
    case FoodVariety
    case Lighting
    case ThermalComfort
    case Sleep
    case InjuriesDisease
    case Bodyweight
    case Pain
    case StimulatingEnvironment
    case Room
    case Exercise
    case SameSpeciesInteraction
    case OtherSpeciesConflict
    case OtherSpeciesInteraction
    case CarerBond
    case PeopleInteraction
    case CalmWithPeople

val allQuestions:Seq[(QuestionSection, Seq[Question])] = Seq(
    Domain.Mental -> Seq(
        Question(0, Domain.Mental, { 
            (a:Animal) => s"Overall, how would you rate ${a.name}'s wellbeing?" }, 
            (_) => "From a high level persepective (or 'blurring your vision'), what would you consider the animal's overall well-being to be?",
            (a:Animal) => s"""Over time, the more detailed questions in each category will help the app to understand what domains to focus on for ${a.name}. Keeping track of a human high-level judgment of the animal's wellbeing, however, helps to understand what detailed changes will predict declines and improvements in overall well-being.""",
            QuestionType.RatingPicker
        ),
    ),
    Domain.Nutrition -> Seq(
        Question(1, Domain.Nutrition, { 
            (a:Animal) => s"${a.name} drinks enough clean water." },
            (_) => "Can the animal access clean water at all times?",
            (_) => """|From a welfare assessment perspective, the welfare compromise occurs when the animal is not able to resolve their need to drink. A thirsty animal deprived from water will experience frustration. 
                      |""".stripMargin
        ),
        Question(2, Domain.Nutrition, { 
            (a:Animal) => s"${a.name} eats sufficient, nutritious, and palatable food." },
            (_) => "Do the animal's diet and feed intake meet its nutritional and behaviour needs?",
            (_) => """|From a welfare perspective, when we talk about animals receiving sufficient food, or animals being hungry, we are not talking about them being too thin or malnourished. We are referring to them fasting for periods longer than are healthy and natural. 
                      |""".stripMargin
        ),

        Question(3, Domain.Nutrition, { 
            (a:Animal) => s"${a.name} is offered food in ways that are varied and interesting for their species." },
            (_) => "Consider not simply what the animal is eating but how they are eating – the behavioural aspects – and whether the food is presented in ways that suit their species",
            (_) => """|The key to responding to this statement is to assess whether the way the food is presented is more, or less, compatible with the animal’s evolved characteristics.
                      |""".stripMargin            
        ),    
    ),
    Domain.Environment -> Seq(
        Question(4, Domain.Environment, { 
            (a:Animal) => s"${a.name} can avoid unpleasant lighting levels, noises, and odours." },
            (_) => "Here we encourage you to think about all the events that animals of this species naturally seek to avoid.",
            (_) => """Just because an animal tolerates a noise, an odour or a certain level of lighting does not mean that they woud rather not experience it."""
        ),
        Question(5, Domain.Environment, { 
            (a:Animal) => s"${a.name} can choose to occupy areas that are clean and promote physical and thermal comfort." },
            (_) => "Animals naturally avoid discomfort and infection and flourish, so they need housing that protects them from the physical environment",
            (_) => """The enclosure should be well drained, clean and compliant and allow that animal to move around, stand or lie down on a surface that suits their species"""
        ),
        Question(6, Domain.Environment, { 
            (a:Animal) => s"${a.name} lives in an environment that allows them to be calm, to rest and to sleep." },
            (_) => "Check for environmental, physical or psychological stressors that might lead to reduced sleep",
            (_) => """The animal can sleep where and when it chooses to"""
        ),    
    ),
    Domain.Health -> Seq(
        Question(7, Domain.Health, { 
            (a:Animal) => s"${a.name} is free from injury and signs of disease" },
            (_) => "The aim here, is to present the general, practical, and evidence-based indicators of good and ill health that may impact on the animal’s experience. ",
            (_) => """This does not replace either a professional exam nor expert advice in any way. Veterinarians are essential members of your animal’s health support team"""
        ),
        Question(8, Domain.Health, { 
            (a:Animal) => s"${a.name} has a healthy bodyweight and an appropriate level of fitness" },
            (_) => "You can think of body condition as ‘fatness’, and condition scoring as a reliable way to tell if a animal’s diet is providing sufficient, too much or too little energy for them to execute their daily activities.",
            (_) => """Although body condition is related to the animal’s nutrition, in the Health Domain, the concern is with its influence on physical and mental (body) function. It is relevant because carrying the right amount of ‘fatness’ is associated with healthy body function, whereas animals who are either too thin or too fat can suffer various health problems."""
        ),
        Question(9, Domain.Health, { 
            (a:Animal) => s"${a.name} shows no signs of acute or chronic pain, or distress." },
            (_) => "The animal is moving, eating, resting and interacting normally with no apparent lameness or stiffness ",
            (_) => """As well as assessing the welfare of your own animals, you should feel comfortable asking a good, knowledgeable friend or colleague to do so. Learning about these discomfort, pain and stress behaviours can help us identify what’s going on for the animal and how they are experiencing the world.You might know the signs of disease and pain, but fail to recognise them in your own animals. This can be especially noticeable when the ailment has a slow onset or is chronic.
Such ‘blind spots’ can happen because we become habituated or desensitised. """"
        ),    
    ),
    Domain.InteractionsEnvironment -> Seq(
        Question(10, Domain.InteractionsEnvironment, { 
            (a:Animal) => s"${a.name} lives in an interesting and stimulating habitat" },
            (_) => "The animal regularly uses all areas of their enclosure and finds exploration of the enclosure satisfying because it contains stimuli that are relevant to their needs",
            (_) => """As well as being nourished, thermally comfortable, and rested, animals (like us) need to be physically and mentally occupied. """
        ),
        Question(11, Domain.InteractionsEnvironment, { 
            (a:Animal) => s"${a.name} has enough room to move around freely" },
            (_) => "This statement is especially relevant to animal living in artificially built environments, which are much smaller than their natural habitats.",
            (_) => """From the animal’s perspective, anything smaller than a natural home range, is in principle, a compromise! But don’t give up, instead, you can apply the following rules of thumb: The longer an animal spends in an enclosure, the larger the enclosure  should be. The more choice (agency) the animal has to leave the enclosure, the more its management is aligned with their telos. Whenever confinement is imposed by the human carers or where the animal has no real choice, the general principle is that shorter periods of confinement are better."""
        ),
        Question(12, Domain.InteractionsEnvironment, { 
            (a:Animal) => s"${a.name} can choose to exercise and explore freely." },
            (_) => "Psychologically, exploring is linked with positive experiences of anticipation and goal achievement, and over time, it contributes memories of success that can be measured as optimism.",
            (_) => """  Exploring occupies a animal’s time in a pleasurable and healthy way, and it has numerous benefits."""
        ),    
    ),
    Domain.InteractionsSocial -> Seq(
        Question(13, Domain.InteractionsSocial, { 
            (a:Animal) => s"${a.name} can choose how long they spend interacting with animals of their own species" },
            (_) => "Here we are mainly looking at whether the facilities and management schedules afford them any agency – that is, the opportunity to choose to interact or not interact with other members of their species, and how long for.",
            (_) => """Retrieving data. Wait a few seconds and try to cut or copy again.By observing the choices the animal makes and their behaviour towards others, you can learn a lot about their subjective experience - and whether the interactions they engage in are giving rise to negative, neutral or positive experiences at least at the time of the assessment."""
        ),
        Question(14, Domain.InteractionsSocial, { 
            (a:Animal) => s"${a.name} has the space to avoid conflicts with animals of other species" },
            (_) => "There are other situations where confined animals do not have the space to avoid conflicts with members of other species. Whether the reason they can’t avoid conflict is because they are confined, tethered, held, or cornered by another animal, the correct decision would be to strongly disagree with the statement.",
            (_) => """"Another reason to disagree with the statement is where the animal does not have the space, or the shape and design features of their housing do not allow them to avoid conflict with animals of other species who are present at the time of the assessment. """"
        ),
        Question(15, Domain.InteractionsSocial, { 
            (a:Animal) => s"${a.name} can choose to interact appropriately with animals of other species." },
            (_) => "To decide on this question, we first need to consider what the term “appropriately” refers to, because what matters is what animals of this species may consider ‘appropriate’ – from their perspective. This is easier to do if we remind ourselves of the nature of the species in question.",
            (_) => """It is reasonable to say that, in most cases, from the animal’s perspective, maintaining a sense of safety during such interactions would be their very top priority."""
        ),    
    ),
    Domain.InteractionsHuman -> Seq(
        Question(16, Domain.InteractionsHuman, { 
            (a:Animal) => s"${a.name} has healthy bonds with their carer(s)" },
            (_) => "The term bond - or attachment bond - refers to the emotional connection that develops between animals of the same species or between humans and animals. Here, we can focus on one of the most useful pre-requisites to attachment – and that is ‘trust’.",
            (_) => """Trust is associated with positive feelings of predictability and, it is built, maintained or eroded over time. Trust contributes to a sense of safety and security, and this is a positive subjective experience that is compatible with good welfare."""
        ),
        Question(17, Domain.InteractionsHuman, { 
            (a:Animal) => s"${a.name} interacts with familiar people confidently and safely" },
            (_) => "During their lifetime, domestic animals often have to interact with many different humans – and every interaction will leave them feeling either negative, neutral or positive about the experience. It is important that animals generalize their trust, so they behave appropriately and safely with all humans, not just their main care providers.",
            (_) => """During their lifetime, domestic animals often have to interact with many different humans – and every interaction will leave them feeling either negative, neutral or positive about the experience. It is important that animals generalize their trust, so they behave appropriately and safely with all humans, not just their main care providers."""
        ),    
        Question(18, Domain.InteractionsHuman, { 
            (a:Animal) => s"${a.name} is calm in the presence of most people" },
            (_) => "For this statement, focus on the interaction – and whether the animal responds appropriately to the carer. By appropriately, we are referring to the animal showing the qualities of good training; they consistently and calmly respond to light signals or cues and; they keep doing what they have been cued to do, without having to be constantly corrected, and without having to use strong pressures or coercive measures.",
            (_) => """We know that the absence of coercion and responding to subtle cues are the keys to an animal feeling in control, safe and confident, and these qualities are compatible with enhanced welfare,"""
        ),
    ),
)

lazy val questionMap = allQuestions.flatMap { case (s, qs) => qs.map(q => q.num -> q) }.toMap

val flattenedQs = for 
    (_, qs) <- allQuestions
    q <- qs
yield q

// The questions for a given domain
def domainQuestions(d:Domain) = flattenedQs.filter(_.domain == d)

enum AnswerValue derives upickle.default.ReadWriter:
    case Numeric(value:Double)
    case Rated(value:Rating)

    def asDouble = this match {
        case Numeric(v) => v
        case Rated(r) => r.value
    }

    def labelText = this match {
        case Numeric(v) => scoreText(v) //f" ${v * 100}%2.0f"
        case Rated(r) => scoreText(r)
    }

