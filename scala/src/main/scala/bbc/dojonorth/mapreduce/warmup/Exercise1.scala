package bbc.dojonorth.mapreduce.warmup

class Exercise1 {

  def sumOfList(xs: List[Int]): Int = ???

  def sumOfSquaresOfList(xs: List[Int]): Int = ???

  def productOfDoubleOfSquaresOfList(xs: List[Int]): Int = ???

  // mapReduce is a higher-order function
  def mapReduce(mapFn: (Int => Int), reduceFun: (Int,Int) => Int, input: List[Int]): Int = ???

}
