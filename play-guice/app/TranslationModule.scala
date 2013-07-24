import com.tzavellas.sse.guice.ScalaModule
import services.{FrenchTranslator, Translator}

/**
 * Configuration module for Guice
 */
class TranslationModule extends ScalaModule {
  def configure() {
    bind[Translator].to[FrenchTranslator]
  }
}
