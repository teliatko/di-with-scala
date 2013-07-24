package controllers

import play.api.mvc.{Action, Controller}
import javax.inject.Inject // (+) Standard Java annotations

import services.Translator

/**
 * Example using Guice
 */
class Translate @Inject()(translator: Translator) extends Controller {

  def greet(name: String) = Action {
    Ok(views.html.greet(translator.translate(s"Hello $name!")))
  }

}
