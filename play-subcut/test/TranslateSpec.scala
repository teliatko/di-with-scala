
import org.scalatest.FreeSpec
import org.scalatest.matchers.MustMatchers
import org.scalatest.matchers.ShouldMatchers
import play.api.GlobalSettings
import play.api.test.{FakeRequest, FakeApplication}
import play.api.test.Helpers._

import com.escalatesoft.subcut.inject.NewBindingModule

import controllers.Translate
import services.{FrenchTranslator, Translator}

class TranslateSpec extends FreeSpec with ShouldMatchers with MustMatchers {

  val name = "Vienna"

  "Translate, should accept name and return proper greeting" - {

    "using application global" in {
      running(FakeApplication()) {
        val translated = route(FakeRequest(GET, "/subcut/" + name)).get

        status(translated) must equal (OK)
        contentType(translated) must be (Some("text/html"))
        contentAsString(translated) should include (name)
      }
    }

    object FixtureTranslationModule extends NewBindingModule( module => {
      import module._
      bind [Translator] toSingle new FrenchTranslator
    })

    object FakeTranslationGlobal extends GlobalSettings {
      override def getControllerInstance[A](clazz: Class[A]): A = {
        new Translate()(FixtureTranslationModule).asInstanceOf[A]
      }
    }

    "mocking application global" in {
      running(FakeApplication(withGlobal = Some(FakeTranslationGlobal))) {
        val translated = route(FakeRequest(GET, "/subcut/" + name)).get

        status(translated) must equal (OK)
        contentType(translated) must be (Some("text/html"))
        contentAsString(translated) should include (name)
      }
    }

    "mocking module" in {
      val controller = new Translate()(FixtureTranslationModule)
      val result = controller.greet(name)(FakeRequest())
      contentAsString(result) should include (name)
    }

    "direct usage" in {
      fail("not applicable, tight coupling")
    }

  }

}
