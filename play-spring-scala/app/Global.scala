import play.api.{Application, GlobalSettings}

import org.springframework.scala.context.function.FunctionalConfigApplicationContext

object Global extends GlobalSettings {

  // Spring-Scala
  private lazy val fnCtx = FunctionalConfigApplicationContext[TranslationConfiguration]

  override def onStart(app: Application) {
    // (+) no need to do anything on start
  }

  override def getControllerInstance[A](clazz: Class[A]) = {
    fnCtx.getBean(clazz) // throws NoSuchBeanDefinitionException when bean is not defined
  }
}
