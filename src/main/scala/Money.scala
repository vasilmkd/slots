class Money(val value: Int) extends AnyVal {

  override def toString: String = s"$$$value"

  def + (that: Money): Money = new Money(value + that.value)

  def - (i: Int): Money = new Money(value - i)
}
