package controllers

import play.api.mvc.{Action, Controller}
import services.{FrenchTranslation, Translation, FrenchTranslator, Translator}

/**
 * Wiring of dependency via traits
 */
object Translate extends Translate with FrenchTranslation

/**
 * Translate controller depends on Translation module
 */
trait Translate extends Controller {
  this: Translation => // Dependency

  def greet(name: String) = Action {
    Ok(views.html.greet(translator.translate(s"Hello $name!")))
  }

}
