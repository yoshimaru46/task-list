import com.typesafe.config.{Config, ConfigFactory}
import scala.collection.JavaConverters._

name := """task-list"""

organization := "com.example"

version := "1.0.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.11"

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-encoding",
  "UTF-8",
  "-Xfatal-warnings",
  "-language:_",
  // Warn if an argument list is modified to match the receiver
  "-Ywarn-adapted-args",
  // Warn about inaccessible types in method signatures.
  "-Ywarn-inaccessible",
  // Warn when a type argument is inferred to be `Any`.
  "-Ywarn-infer-any",
  // Warn when non-nullary `def f()' overrides nullary `def f'
  "-Ywarn-nullary-override",
  // Warn when nullary methods return Unit.
  "-Ywarn-nullary-unit",
  // Warn when numerics are widened.
  "-Ywarn-numeric-widen"
)

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  filters,
  "org.scalatestplus.play" %% "scalatestplus-play"           % "2.0.0" % Test,
  "org.scalatest"          %% "scalatest"                    % "3.0.1" % Test,
  "org.scalikejdbc"        %% "scalikejdbc"                  % "2.5.2",
  "org.scalikejdbc"        %% "scalikejdbc-config"           % "2.5.2",
  "org.scalikejdbc"        %% "scalikejdbc-jsr310"           % "2.5.2",
  "org.scalikejdbc"        %% "scalikejdbc-test"             % "2.5.2" % Test,
  "org.skinny-framework"   %% "skinny-orm"                   % "2.3.7",
  "org.scalikejdbc"        %% "scalikejdbc-play-initializer" % "2.5.+",
  "ch.qos.logback"         % "logback-classic"               % "1.2.3",
  "mysql"                  % "mysql-connector-java"          % "6.0.6",
  "com.adrianhurt"         %% "play-bootstrap"               % "1.1-P25-B3" // 追加
)

// Adds additional packages into Twirl
// TwirlKeys.templateImports ++= Seq(...)

TwirlKeys.templateImports ++= Seq("forms._")

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"

lazy val envConfig = settingKey[Config]("env-config")

envConfig := {
  val env = sys.props.getOrElse("env", "dev")
  ConfigFactory.parseFile(file("env") / (env + ".conf"))
}

flywayLocations := envConfig.value.getStringList("flywayLocations").asScala
flywayDriver := envConfig.value.getString("jdbcDriver")
flywayUrl := envConfig.value.getString("jdbcUrl")
flywayUser := envConfig.value.getString("jdbcUserName")
flywayPassword := envConfig.value.getString("jdbcPassword")
