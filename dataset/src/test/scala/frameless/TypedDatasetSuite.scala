package frameless

import com.holdenkarau.spark.testing.SharedSparkContext
import org.apache.spark.sql.SQLContext
import org.scalatest.FunSuite
import org.scalatest.prop.Checkers

class TypedDatasetSuite extends FunSuite with Checkers with SharedSparkContext {
  implicit def _sc = sc
  implicit def sqlContext = SQLContext.getOrCreate(sc)
}
