package frameless
package forward

import org.scalacheck.Prop
import org.scalacheck.Prop._
import math.Ordering

class IntersectTests extends TypedDatasetSuite {
  test("intersect") {
    def prop[A](data1: Vector[A], data2: Vector[A])(implicit e: TypedEncoder[A], o: Ordering[A]): Prop = {
      val dataset1 = TypedDataset.create(data1)
      val dataset2 = TypedDataset.create(data2)
      val datasetIntersect = dataset1.intersect(dataset2).collect().run().toVector

      // Vector `intersect` is the multiset intersection, while Spark throws away duplicates.
      val dataIntersect = data1.intersect(data2).distinct

      // Comparison done with `.sorted` because order is not preserved by Spark for this operation.
      datasetIntersect.sorted ?= dataIntersect.distinct.sorted
    }

    check(forAll(prop[Int] _))
    check(forAll(prop[String] _))
  }
}