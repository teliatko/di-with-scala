package services

/**
 * Service interface with multiple implementations
 */
trait Translator {
  def translate(what: String): String
}
