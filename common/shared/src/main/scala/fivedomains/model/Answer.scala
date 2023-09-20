package fivedomains.model

case class Answer(q:Int, value:AnswerValue, confidence:Confidence = Confidence.Medium, note: Option[String] = None) {

    lazy val question = questionMap(q)

}