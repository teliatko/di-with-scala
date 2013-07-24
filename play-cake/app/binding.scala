import controllers.Translate
import services.dictionaries.DudenBookshelf
import services.{UniversalTranslation, FrenchTranslation}

/**
 * Binding in package object
 */
package object bakery {

  /** Instance of Translate controller */
  val FrenchTranslate = new Translate with FrenchTranslation
  val GermanTranslate = new Translate with DudenBookshelf with UniversalTranslation
}
