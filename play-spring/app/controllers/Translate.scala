package controllers

import play.api.mvc.{Action, Controller}
import org.springframework.beans.factory.annotation.Autowired

import _root_.services.Translator

/**
 * Example using java version of Spring framework
 */
@org.springframework.stereotype.Controller // (-) annotations are everywhere
class Translate @Autowired()(translator: Translator) extends Controller {

  def greet(name: String) = Action {
    Ok(views.html.greet(translator.translate(s"Hello $name!")))
  }

}
