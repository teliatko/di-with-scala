import play.api.{Application, GlobalSettings}

import com.google.inject.Guice

/**
 * Global object, initialization of DI framework
 */
object Global extends GlobalSettings {
  // Guice
  // (+) injector can be lazy
  private lazy val injector = Guice.createInjector(new TranslationModule)

  override def onStart(app: Application) {
    // (+) no need to do anything on start
  }

  override def getControllerInstance[A](clazz: Class[A]) = {
    injector.getInstance(clazz) // throws ConfigurationException when component is not defined
  }
}
