import org.scalatest.FreeSpec
import org.scalatest.matchers.MustMatchers
import org.scalatest.matchers.ShouldMatchers
import play.api.GlobalSettings
import play.api.test.{FakeRequest, FakeApplication}
import play.api.test.Helpers._

import org.springframework.context.annotation.AnnotationConfigApplicationContext

import controllers.Translate
import services.FrenchTranslator

/**
 * Specification for translator
 */
class TranslateSpec extends FreeSpec with ShouldMatchers with MustMatchers {

  val name = "Vienna"

  "Translate, should accept name and return proper greeting" - {

    "using application global" in {
      running(FakeApplication()) {
        val translated = route(FakeRequest(GET, "/spring/" + name)).get

        status(translated) must equal (OK)
        contentType(translated) must be (Some("text/html"))
        contentAsString(translated) should include (name)
      }
    }

    object FakeTranslationGlobal extends GlobalSettings {
      override def getControllerInstance[A](clazz: Class[A]): A = {
        new Translate(new FrenchTranslator).asInstanceOf[A]
      }
    }

    "mocking application global" in {
      running(FakeApplication(withGlobal = Some(FakeTranslationGlobal))) {
        val translated = route(FakeRequest(GET, "/spring/" + name)).get

        status(translated) must equal (OK)
        contentType(translated) must be (Some("text/html"))
        contentAsString(translated) should include (name)
      }
    }

    class FakeGlobal(clazzes: Class[_ <: AnyRef]*) extends GlobalSettings {
      val ctx = new AnnotationConfigApplicationContext(clazzes : _*)
      override def getControllerInstance[A](clazz: Class[A]): A = {
        ctx.getBean(clazz)
      }
    }

    "mocking application global and context" in {
      running(FakeApplication(withGlobal = Some(new FakeGlobal(classOf[Translate], classOf[FrenchTranslator])))) {
        val translated = route(FakeRequest(GET, "/spring/" + name)).get

        status(translated) must equal (OK)
        contentType(translated) must be (Some("text/html"))
        contentAsString(translated) should include (name)
      }
    }

    "direct usage" in {
      val controller = new Translate(new FrenchTranslator)
      val result = controller.greet(name)(FakeRequest())
      contentAsString(result) should include (name)
    }

  }

}
