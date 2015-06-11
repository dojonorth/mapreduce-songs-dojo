package bbc.dojonorth.mapreduce

import scala.collection.immutable.HashMap

/**
 * Utility functions for exercises
 */
object Helpers {
  implicit def convertMapToHashMap(s: Map[String, Int]): HashMap[String, Int] = {
    var newMap: HashMap[String, Int] = HashMap()
    s.foreach { case (key,value) =>
      newMap += (key->value)
    }
    newMap
  }
}
