package services


/**
 * Module, defines provided services
 */
trait Translation { // Module or Component
  def translator: Translator // Services it provides
}

/**
 * Service interface with multiple implementations
 */
trait Translator {
  def translate(what: String): String
}
