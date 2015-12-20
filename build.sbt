lazy val root = (project in file(".")).
  settings(
    name := "YSCDUtil",
    version := "0.0.1",
    scalaVersion := "2.11.7",
    libraryDependencies ++= Seq(
    	"org.apache.commons" % "commons-lang3" % "3.4"
    	, "com.fasterxml.jackson.core" % "jackson-databind" % "2.6.4"
    	, "org.apache.logging.log4j" % "log4j-api" % "2.5"
    	, "org.apache.logging.log4j" % "log4j-core" % "2.5"
    	, "org.mapdb" % "mapdb" % "1.0.8"
    	, "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
    ),
    scalacOptions in (Compile, doc) ++= Seq("-author"),
    packAutoSettings
)