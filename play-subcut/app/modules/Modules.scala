package modules

import com.escalatesoft.subcut.inject.NewBindingModule

import services.{Translator, FrenchTranslator}

object Modules {

  val translationModule = new NewBindingModule( module => {
    import module._

    bind [Translator] toSingle new FrenchTranslator
  })

}
