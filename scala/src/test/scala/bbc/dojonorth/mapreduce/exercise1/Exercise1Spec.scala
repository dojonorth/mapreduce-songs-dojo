package bbc.dojonorth.mapreduce.exercise1

import bbc.dojonorth.mapreduce.Exercise1
import org.scalatest.{Matchers, FlatSpec}

class Exercise1Spec extends FlatSpec with Matchers {

  "A sum list function" should "return 0 for empty list" in {
    val testList: List[Int] = Nil

    Exercise1.sumOfList(testList) should be (0)
  }

  it should "return 10 for List(1,2,3,4)" in {
    val testList = List(1,2,3,4)
    val expectedSum = testList.sum

    Exercise1.sumOfList(testList) should be (expectedSum)
  }

  "A sum of squares function" should "return the correct sum" in {
    val testList = List(1,2,3)
    val expectedSum = 1*1 + 2*2 + 3*3

    Exercise1.sumOfSquaresOfList(testList) should be (expectedSum)
  }

  "A `product of squares of doubled numbers` function" should "return the correct product" in {
    val testList = List(1,2,3)
    val expectedProduct = ((1*1)*2) * ((2*2)*2) * ((3*3)*2)

    Exercise1.productOfDoubleOfSquaresOfList(testList) should be (expectedProduct)
  }

  it should "work with an empty list" in {
    val testList: List[Int] = Nil
    val expectedProduct = 0

    Exercise1.productOfDoubleOfSquaresOfList(testList) should be (expectedProduct)
  }

  "A mapReduce function" should "take a simple map and reduce function and apply them to a list of integers correctly" in {
    val testMapFn = (x: Int) => x*2
    val testReduceFn = (x:Int , y: Int) => (x*2)+y
    val testList = List(1,2,3,4)

    val expectedResult = 52
    Exercise1.mapReduce(testMapFn, testReduceFn, testList) should be (expectedResult)
  }
}
