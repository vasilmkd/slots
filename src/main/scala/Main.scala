import Input._
import Game._

object Main {

  private def showInstructions(answer: String): Unit = {
    if (answer == Yes)
      println(
        """
          |If you put a dollar into a slot machine and pull the handle on its side,
          |the wheels spin around and eventually come to rest in some new configuration.
          |If the configuration matches one of a set of winning patterns printed on the front
          |of the slot machine, you get back some money. If not, you're out a dollar. The
          |following table shows a set of winning patterns, along with their associated payoffs:
          |
          |BAR      BAR      BAR          pays   $250
          |BELL     BELL     BELL/BAR     pays   $20
          |PLUM     PLUM     PLUM/BAR     pays   $14
          |ORANGE   ORANGE   ORANGE/BAR   pays   $10
          |CHERRY   CHERRY   CHERRY       pays   $7
          |CHERRY   CHERRY   ---          pays   %5
          |CHERRY   ---      ---          pays   %2
        """.stripMargin)
  }

  private def gameLoop(money: Money): Unit = {
    println()
    if (money.value == 0) {
      println(s"You have $money. You lost everything.")
      return
    }

    print(s"You have $money. Would you like to play? ")

    val inputResult = readAnswer()

    inputResult match {
      case Failure(message) =>
        println(message)
        gameLoop(money)
      case Success(message) =>
        val result = play(message, money)
        result match {
          case Win(newMoney) =>
            println(s" -- You win $newMoney.")
            gameLoop(money + newMoney)
          case Lose() =>
            println(s" -- You lose.")
            gameLoop(money - 1)
          case WalkAway(winnings) =>
            println(s"You walked away with $winnings.")
        }
    }
  }

  def main(args: Array[String]): Unit = {
    print("Would you like instructions? ")

    val inputResult = readAnswer()

    inputResult match {
      case Success(answer) => showInstructions(answer)
      case Failure(message) =>
        println(message)
        println("You probably need instructions.")
        showInstructions(Yes)
    }

    val money = new Money(50)

    gameLoop(money)
  }
}
