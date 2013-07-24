import org.scalatest.FreeSpec
import org.scalatest.matchers.MustMatchers
import org.scalatest.matchers.ShouldMatchers
import play.api.GlobalSettings
import play.api.mvc.{RequestHeader, Handler}
import play.api.test.{FakeRequest, FakeApplication}
import play.api.test.Helpers._

import controllers.Translate
import services.dictionaries.DudenBookshelf
import services.UniversalTranslation

class TranslateSpec extends FreeSpec with ShouldMatchers with MustMatchers {

  val name = "Vienna"

  "Translate, should accept name and return proper greeting" - {

    "using application global" in {
      running(FakeApplication()) {
        val translated = route(FakeRequest(GET, "/cake/" + name)).get

        status(translated) must equal (OK)
        contentType(translated) must be (Some("text/html"))
        contentAsString(translated) should include (name)
      }
    }

    object FakeTranslationGlobal extends GlobalSettings {

      private val Name = """/cake/([^/]+)""".r

      override def onRouteRequest(req: RequestHeader): Option[Handler] = {
        (req.method, req.path) match {
          case ("GET", Name(name)) => {
            Some(bakery.FrenchTranslate.greet(name))
          }
          case _ => None
        }
      }
    }

    "mocking application global" in {
      running(FakeApplication(withGlobal = Some(FakeTranslationGlobal))) {
        val translated = route(FakeRequest(GET, "/cake/" + name)).get

        status(translated) must equal (OK)
        contentType(translated) must be (Some("text/html"))
        contentAsString(translated) should include (name)
      }
    }

    "direct usage" in {
      val controller = new Translate with UniversalTranslation with DudenBookshelf
      val result = controller.greet(name)(FakeRequest())
      contentAsString(result) should include (name)
    }
  }

}
