import play.api.templates._

import scala.collection.JavaConverters._

package helpers {

  object repeat {

    def apply(field: play.api.data.Field, min: Int = 1)(f: (Int, play.api.data.Field) => Html) = {
      (0 until math.max(if (field.indexes.isEmpty) 0 else field.indexes.max + 1, min)).map(i => f(i, field("[" + i + "]")))
    }

  }

  object displayIfError {
    def apply(field: play.api.data.Field, toDisplay: String): String = {

      if (field.hasErrors) {
        return toDisplay;
      }

      return "";
    }
  }

  object displayIf {
    def apply(condition: Boolean, toDisplay: String): String = {

      if (condition) {
        return toDisplay;
      }

      return "";
    }
  }
}