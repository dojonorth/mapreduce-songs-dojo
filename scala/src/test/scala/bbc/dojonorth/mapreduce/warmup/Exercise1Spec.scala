package bbc.dojonorth.mapreduce.warmup

import org.scalatest.{FlatSpec, Matchers}

class Exercise1Spec extends FlatSpec with Matchers {

  val exercise1Implementation = new Exercise1

  "A sum list function" should "return 0 for empty list" in {
    val testList: List[Int] = Nil

    exercise1Implementation.sumOfList(testList) should be (0)
  }

  it should "return 10 for List(1,2,3,4)" in {
    val testList = List(1,2,3,4)
    val expectedSum = testList.sum

    exercise1Implementation.sumOfList(testList) should be (expectedSum)
  }

  "A sum of squares function" should "return the correct sum" in {
    val testList = List(1,2,3)
    val expectedSum = 1*1 + 2*2 + 3*3

    exercise1Implementation.sumOfSquaresOfList(testList) should be (expectedSum)
  }

  "A `product of squares of doubled numbers` function" should "return the correct product" in {
    val testList = List(1,2,3)
    val expectedProduct = ((1*1)*2) * ((2*2)*2) * ((3*3)*2)

    exercise1Implementation.productOfDoubleOfSquaresOfList(testList) should be (expectedProduct)
  }

  it should "work with an empty list" in {
    val testList: List[Int] = Nil
    val expectedProduct = 0

    exercise1Implementation.productOfDoubleOfSquaresOfList(testList) should be (expectedProduct)
  }

  "A mapReduce function" should "take a simple map and reduce function and apply them to a list of integers correctly" in {
    val testMapFn = (x: Int) => x*2
    val testReduceFn = (x:Int , y: Int) => (x*2)+y
    val testList = List(1,2,3,4)

    val expectedResult = 52
    exercise1Implementation.mapReduce(testMapFn, testReduceFn, testList) should be (expectedResult)
  }
}
