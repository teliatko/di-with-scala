import org.scalatest.FreeSpec
import org.scalatest.matchers.MustMatchers
import org.scalatest.matchers.ShouldMatchers
import play.api.GlobalSettings
import play.api.test.{FakeRequest, FakeApplication}
import play.api.test.Helpers._

import com.tzavellas.sse.guice.ScalaModule
import com.google.inject.Guice

import controllers.Translate
import services.{Translator, FrenchTranslator}

class TranslateSpec extends FreeSpec with ShouldMatchers with MustMatchers {

  val name = "Vienna"

  "Translate, should accept name and return proper greeting" - {

    "using application global" in {
      running(FakeApplication()) {
        val translated = route(FakeRequest(GET, "/guice/" + name)).get

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
        val translated = route(FakeRequest(GET, "/guice/" + name)).get

        status(translated) must equal (OK)
        contentType(translated) must be (Some("text/html"))
        contentAsString(translated) should include (name)
      }
    }

    val fakeModule = new ScalaModule {
      def configure() {
        bind[Translator].to[FrenchTranslator]
      }
    }

    class FakeGlobal(module: ScalaModule) extends GlobalSettings {
      val injector = Guice.createInjector(module)
      override def getControllerInstance[A](clazz: Class[A]): A = {
        injector.getInstance(clazz)
      }
    }

    "mocking application global and module" in {
      running(FakeApplication(withGlobal = Some(new FakeGlobal(fakeModule)))) {
        val translated = route(FakeRequest(GET, "/guice/" + name)).get

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
