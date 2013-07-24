package services.dictionaries

/**
 * Module, defines provided services
 */
trait Bookshelf { // Module or Component

  def dictionary: Dictionary // Services it provides

  trait Dictionary { // Service interface
    def lookup(word: String): Option[String]
  }

}
