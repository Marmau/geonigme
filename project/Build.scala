import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

  val appName = "Geonigme"
  val appVersion = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here
    "org.openrdf.sesame" % "sesame-runtime" % "2.6.9",
    "org.openrdf.alibaba" % "alibaba-runtime" % "2.0-rc5"
    //"crionics" %% "play2-authenticitytoken" % "1.0-SNAPSHOT"
  )

  val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
    // Add your own project settings here    
    resolvers += "Aduna Open Source" at "http://repo.aduna-software.org/maven2/releases/",
    resolvers += "Crionics Github Repository" at "http://orefalo.github.com/m2repo/releases/")
}


