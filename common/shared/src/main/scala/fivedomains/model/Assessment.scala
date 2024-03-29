package fivedomains.model

case class Assessment(animal:AnimalId, situation:Situation, time:Double, answers:Map[Int, Answer]) {

    /** Average score in a given domain */
    def average(domain:Domain):Double = domain match {
        case Domain.Mental => 
            answers.values.map(_.value.asDouble).sum / answers.size
        case d => 
            val filtered = answers.values.filter { (a) => flattenedQs.find(_.num == a.q).map(_.domain).contains(d) }
            filtered.map(_.value.asDouble).sum / filtered.size
    }

    def answersBelow(level:Double) = answers.values.filter(_.value.asDouble <= level)

    // Answers rated poor or worse
    lazy val lowAnswers = answersBelow(Rating.Poor.value)

    lazy val domainsContainingConcern = Domain.values.filter(d => lowAnswers.exists(a => a.question.domain == d))

    def overallConfidence = answers.values.map(_.confidence.value).sum / answers.size

    def answer(q:Question) = answers(q.num)


    def heuristic(numbers:Iterable[Double]):Double = 
        val minScore = numbers.min
        val avgScore = numbers.sum / numbers.size
        Math.min(avgScore, minScore + 20)

    def answersInDomain(d:Domain) = 
        answers.values.filter { (a) => flattenedQs.find(_.num == a.q).map(_.domain).contains(d) }

    def categoryScore(d:Domain):Double = 
        val filtered = answers.values.filter { (a) => flattenedQs.find(_.num == a.q).map(_.domain).contains(d) }
        val numeric = filtered.map(_.value.categoryMidpoint)
        heuristic(numeric)

    def overallScore:Double = 
        val catScores = for d <- Domain.scoredDomains yield categoryScore(d)
        heuristic(catScores)

}

extension (s:Iterable[Assessment]) {
    def overallConfidence = s.map(_.overallConfidence).sum / s.size
}