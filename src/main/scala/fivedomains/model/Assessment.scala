package fivedomains.model

case class Assessment(animal:AnimalId, time:Double, answers:Map[Int, Answer]) {

    /** Average score in a given domain */
    def average(domain:Domain):Double = domain match {
        case Domain.Mental => 
            answers.values.map(_.value).sum / answers.size
        case d => 
            val filtered = answers.values.filter { (a) => flattenedQs.find(_.num == a.q).map(_.domain).contains(d) }
            filtered.map(_.value).sum / filtered.size
    }

    def overallConfidence = answers.values.map(_.confidence.value).sum / answers.size

}

extension (s:Iterable[Assessment]) {
    def overallConfidence = s.map(_.overallConfidence).sum / s.size
}