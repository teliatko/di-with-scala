package controllers

import play.api.mvc.{Action, Controller}

import services.Translator

/**
 * Example using scala version of Spring framework
 */
class Translate (translator: Translator) extends Controller {

  def greet(name: String) = Action {
    Ok(views.html.greet(translator.translate(s"Hello $name!")))
  }

}
