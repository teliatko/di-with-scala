package controllers

import play.api.mvc.{Action, Controller}
import com.escalatesoft.subcut.inject.{Injectable, BindingModule}

import modules.Modules._
import services.Translator

// (-) relying heavily on implicits
object Translate extends Translate()(translationModule)

// (-) tight coupling to Subcut API (BindingModule, Injectable)
class Translate(implicit val bindingModule: BindingModule) extends Controller with Injectable {

  val translator = inject[Translator] // (-) basically service locator pattern, not true DI

  def greet(name: String) = Action {
    Ok(views.html.greet(translator.translate(s"Hello $name!")))
  }
}

