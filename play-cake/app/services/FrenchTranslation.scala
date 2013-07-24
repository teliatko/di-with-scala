package services

/**
 * Implementation of translator to french
 */
class FrenchTranslator extends Translator { // (+) no need for annotation on provider side
  val wordReplacements = Map(
    "hello"     -> "bonjour",
    "hi"        -> "salut",
    "greetings" -> "salutations"
  )

  def translate(what: String): String = {
    what.split("""\s+""").map { word =>
      wordReplacements.get(word.toLowerCase).map { translation =>
        word match {
          case _ if word(0).isUpper => translation.capitalize
          case _ => translation
        }
      }.getOrElse(word)
    }.mkString(" ")
  }
}

/**
 * Implementation of french translation module
 */
trait FrenchTranslation extends Translation {
  override val translator = new FrenchTranslator // Singleton, def is overridden with val
}
