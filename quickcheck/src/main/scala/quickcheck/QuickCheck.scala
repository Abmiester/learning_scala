package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  property("min1") = forAll { a: Int =>
    val h = insert(a, empty)
    findMin(h) == a
  }

  property("min2") = forAll { h: H =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  property("inserting_two_els_into_emp_heap") = forAll { (a: A, b: A) =>
    val h = insert(a, insert(b, empty))
    val m = findMin(h)
    m == ord.min(a, b)
  }

  property("insert_delete_min") = forAll { a: A =>
    val h = insert(a, empty)
    isEmpty(deleteMin(h))
  }

  property("melding_min") = forAll { (h1: H, h2: H) =>
    val minMelded = findMin( meld(h1, h2) )
    minMelded == findMin(h1) || minMelded == findMin(h2)
  }

  property("inserting_sequence") = forAll { l: List[A] =>
    val h = l.foldRight(empty)(insert)
    asList(h) == l.sorted
  }

  property("asList_sorted") = forAll { h: H =>
    asList(h).sorted == asList(h)
  }

  def asList(h: H): List[A] = if (isEmpty(h)) List() else findMin(h) :: asList(deleteMin(h))

  lazy val genHeap: Gen[H] = for {
    k <- arbitrary[A]
    h <- oneOf(Gen.const(empty), genHeap)
  } yield insert(k, h)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

}
