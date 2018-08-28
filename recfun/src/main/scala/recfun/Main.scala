package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1, Pascal's Triangle: The numbers at the edge of the triangle are all 1,
    * and each number inside the triangle is the sum of the two numbers above it.
   */
    def pascal(c: Int, r: Int): Int = {
      if (c == 0 || c == r) 1
      else pascal(c - 1, r - 1) + pascal(c, r - 1)
    }
  
  /**
   * Exercise 2, Parenthesis Balancing: Verifies the balancing of parentheses in a string
   */
    def balance(chars: List[Char]): Boolean = {
      def countparen(chars: List[Char], parenopens: Int): Boolean = {
        if (chars.isEmpty || parenopens <= 0) parenopens == 0
        else if (chars.head == '(') countparen(chars.tail, parenopens + 1)
        else if (chars.head == ')') countparen(chars.tail, parenopens - 1)
        else countparen(chars.tail, parenopens)
      }
      countparen(chars, 0)
    }
  
  /**
   * Exercise 3, Counting Change: Function that counts how many different ways
    * you can make change for an amount, given a list of coin denominations
   */
    def countChange(money: Int, coins: List[Int]): Int = {
      def count(m: Int, c: List[Int]): Int = {
        if (c.isEmpty) 0
        else if (m - c.head == 0) 1
        else if (m - c.head < 0) 0
        else countChange(m - c.head, c) + countChange(m, c.tail )
      }
      count(money, coins.sorted)
    }
  }