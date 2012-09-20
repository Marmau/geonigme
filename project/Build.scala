import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

  val appName = "Geonigme"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    "org.apache.jena" % "jena-core" % "2.7.3",
    "org.apache.jena" % "jena-tdb" % "0.9.3",
    "org.apache.jena" % "jena-arq" % "2.9.3" 
    // Add your project dependencies here
  )

  val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings( 
  	// Add your own project settings here      
  )

}
