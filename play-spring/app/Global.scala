import play.api.{Application, GlobalSettings}

import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext

/**
 * Global object, initialization of DI framework
 */
object Global extends GlobalSettings {
  // Spring framework
  private var ctx : ApplicationContext = _
  // (-) context cannot be lazy val, while it is initialized on different thread
  // private lazy val ctx = new ClassPathXmlApplicationContext("components.xml")

  override def onStart(app: Application) {
    super.onStart(app)
    ctx = new ClassPathXmlApplicationContext("components.xml") // (-) XML is needed
  }

  override def getControllerInstance[A](clazz: Class[A]) = {
    ctx.getBean(clazz) // throws NoSuchBeanDefinitionException when bean is not defined
  }
}
