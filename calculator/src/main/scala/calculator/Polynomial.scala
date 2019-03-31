package calculator

object Polynomial {
  def computeDelta(a: Signal[Double], b: Signal[Double],
      c: Signal[Double]): Signal[Double] = {
    Signal {
      val bval = b()

      (bval * bval) - (4 * a() * c())
    }
  }

  def computeSolutions(a: Signal[Double], b: Signal[Double],
      c: Signal[Double], delta: Signal[Double]): Signal[Set[Double]] = {
    /** Implementing (-b ± √Δ) / (2a)
      */
    val negB = Signal(-1 * b())
    val twoA = Signal(2 * a())
    val rootDelta = Signal(math.sqrt(delta()))

    Signal {
      if (delta() < 0) Set()
      else {
        Set(
          (negB() + rootDelta()) / twoA(),
          (negB() - rootDelta()) / twoA()
        )
      }
    }
  }
}
