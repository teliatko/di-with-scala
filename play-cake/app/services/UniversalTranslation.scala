package services

import services.dictionaries.Bookshelf

/**
 * Implementation of universal translation module
 */
trait UniversalTranslation extends Translation {
  this: Bookshelf => // Dependency

  override val translator = new UniversalTranslator // Provided services

  /**
   * Service implementation
   */
  class UniversalTranslator extends Translator {
    def translate(what: String): String = {
      what.split("""\s+""").map { word =>
        dictionary.lookup(word.toLowerCase).map { translation =>
          word match {
            case _ if word(0).isUpper => translation.capitalize
            case _ => translation
          }
        }.getOrElse(word)
      }.mkString(" ")
    }
  }

}
