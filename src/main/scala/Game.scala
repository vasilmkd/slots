import Input._
import Fruits._

object Game {

  def spin: Configuration = {
    import scala.util.Random
    val random = new Random(System.currentTimeMillis)
    val rand1 = random.nextInt(6)
    val rand2 = random.nextInt(6)
    val rand3 = random.nextInt(6)
    Configuration(fruits(rand1), fruits(rand2), fruits(rand3))
  }

  def play(message: String, money: Money): GameResult =
    if (message == No) WalkAway(money)
    else {
      val config = spin
      print(config)
      config match {
        case Configuration(BAR, BAR, BAR) => Win(new Money(250))
        case Configuration(BELL, BELL, BELL) => Win(new Money(20))
        case Configuration(BELL, BELL, BAR) => Win(new Money(20))
        case Configuration(PLUM, PLUM, PLUM) => Win(new Money(14))
        case Configuration(PLUM, PLUM, BAR) => Win(new Money(14))
        case Configuration(ORANGE, ORANGE, ORANGE) => Win(new Money(10))
        case Configuration(ORANGE, ORANGE, BAR) => Win(new Money(10))
        case Configuration(CHERRY, CHERRY, CHERRY) => Win(new Money(7))
        case Configuration(CHERRY, CHERRY, _) => Win(new Money(5))
        case Configuration(CHERRY, _, _) => Win(new Money(2))
        case _ => Lose()
      }
    }
}

sealed trait GameResult

case class Win(money: Money) extends GameResult
case class Lose() extends GameResult
case class WalkAway(money: Money) extends GameResult

object Fruits {
  sealed abstract class Fruit(name: String, val num: Int) {
    override def toString: String = name + " " * (6 - name.length)
  }

  case object BAR extends Fruit("BAR", 0)
  case object BELL extends Fruit("BELL", 1)
  case object PLUM extends Fruit("PLUM", 2)
  case object ORANGE extends Fruit("ORANGE", 3)
  case object CHERRY extends Fruit("CHERRY", 4)
  case object LEMON extends Fruit("LEMON", 5)

  val fruits = Map(BAR.num -> BAR, BELL.num -> BELL, PLUM.num -> PLUM,
    ORANGE.num -> ORANGE, CHERRY.num -> CHERRY, LEMON.num -> LEMON)
}

case class Configuration(first: Fruit, second: Fruit, third: Fruit) {
  override def toString: String = s"$first   $second   $third"
}
