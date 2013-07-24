import org.springframework.scala.context.function.FunctionalConfiguration

import controllers.Translate
import services.FrenchTranslator

class TranslationConfiguration extends FunctionalConfiguration {

  val translator = bean() { // Bean is registered under auto-generated name, it's resolved by type
    new FrenchTranslator
  }

  bean() {
    new Translate(translator())
  }

}