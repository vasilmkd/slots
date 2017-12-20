import scala.io.StdIn

object Input {
  final val Yes: String = "yes"
  final val No: String = "no"

  def readAnswer(): InputResult = Option(StdIn.readLine()) match {
    case Some(answer) =>
      if (answer.toLowerCase == Yes || answer.toLowerCase == No) Success(answer)
      else Failure("Unknown answer. Please answer with 'yes' or 'no'.")
    case None => Failure("Please try again.")
  }
}

sealed trait InputResult

case class Success(answer: String) extends InputResult
case class Failure(message: String) extends InputResult
