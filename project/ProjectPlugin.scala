import org.scalajs.sbtplugin.ScalaJSPlugin
import sbt._
import sbtindigo.SbtIndigo

object ProjectPlugin extends AutoPlugin {

  object autoImport {
    private lazy val kindProjectorV = "0.11.0"
    addCompilerPlugin("org.typelevel" %% "kind-projector" % kindProjectorV cross CrossVersion.full)
  }

  override def requires: Plugins = ScalaJSPlugin && SbtIndigo

  override def trigger: PluginTrigger = allRequirements

}
