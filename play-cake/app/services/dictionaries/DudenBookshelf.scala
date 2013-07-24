package services.dictionaries

/**
 * Implementation of component
 */
trait DudenBookshelf extends Bookshelf {

  override def dictionary = new DudenDictionary // Prototype, each invocation will return new instance

  class DudenDictionary extends Dictionary {
    val wordReplacements = Map(
      "hello"     -> "Hallo",
      "hi"        -> "Servus",
      "greetings" -> "Gruss"
    )

    def lookup(word: String): Option[String] = wordReplacements.get(word)
  }

}
