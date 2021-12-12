name := "gabrielVolpe"

version := "0.1"

scalaVersion := "2.13.5"

val Http4sVersion              = "0.23.6"
val CirceVersion               = "0.14.1"
val MunitVersion               = "0.7.29"
val LogbackVersion             = "1.2.7"
val MunitCatsEffectVersion     = "1.0.6"

val catsCoreVersion            = "2.7.0"
val catsEffectVersion          = "3.3.0"
val catsMtlVersion             = "1.2.1"
val fs2Version                 = "3.2.2"
val monocleCoreVersion         = "3.1.0"
val monocleMacroVersion        = "3.1.0"
val newTypeVersion             = "0.4.4"
val refinedVersion             = "0.9.28"
val refinedCatsVersion         = "0.9.28"
val derevoCatsVersion          = "0.12.7"
val derevoCatsTaglessVersion   = "0.12.7"
val derevoCirceMagnoliaVersion = "0.12.7"
val tofuHigherKindVersion      = "0.10.4"
val kindProjectorVersion       = "0.13.2"
val squantsMoneyVersion        = "1.8.3"

val cirisVersion               = "2.3.1"
val skunkVersion               = "0.2.2"

lazy val root = (project in file("."))
  .settings(
    name := "minimal",
    libraryDependencies ++= Seq(
      "org.http4s"      %% "http4s-ember-server" % Http4sVersion,
      "org.http4s"      %% "http4s-ember-client" % Http4sVersion,
      "org.http4s"      %% "http4s-circe"        % Http4sVersion,
      "org.http4s"      %% "http4s-dsl"          % Http4sVersion,
      "io.circe"        %% "circe-generic"       % CirceVersion,
      "ch.qos.logback"  %  "logback-classic"     % LogbackVersion,
      "org.scalameta"   %% "svm-subs"            % "20.2.0",

      "org.typelevel" %% "cats-core"             % catsCoreVersion,
      "org.typelevel" %% "cats-effect"           % catsEffectVersion,
      "org.typelevel" %% "cats-mtl"              % catsMtlVersion,
      "org.typelevel"  %% "squants"              % squantsMoneyVersion,
      "co.fs2"        %% "fs2-core"              % fs2Version,
      "dev.optics"    %% "monocle-core"          % monocleCoreVersion,
      "dev.optics"    %% "monocle-macro"         % monocleMacroVersion,
      "io.estatico"   %% "newtype"               % newTypeVersion,
      "eu.timepit"    %% "refined"               % refinedVersion,
      "eu.timepit"    %% "refined-cats"          % refinedCatsVersion,
      "tf.tofu"       %% "derevo-cats"           % derevoCatsVersion ,
      "tf.tofu"       %% "derevo-cats-tagless"   % derevoCatsTaglessVersion ,
      "tf.tofu"       %% "derevo-circe-magnolia" % derevoCirceMagnoliaVersion ,
      "tf.tofu"       %% "tofu-core-higher-kind" % tofuHigherKindVersion,

      "is.cir"        %% "ciris"                 % cirisVersion,
      "is.cir"        %% "ciris-refined"         % cirisVersion,
      "is.cir"        %% "ciris-enumeratum"      % cirisVersion,

      "org.tpolecat" %% "skunk-core"             % skunkVersion
    ),
    addCompilerPlugin("org.typelevel" %% "kind-projector"     % "0.13.2" cross CrossVersion.full),
    addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.1"),
    testFrameworks += new TestFramework("munit.Framework"),
    scalacOptions
      ++= Seq(
      "-Ymacro-annotations",
      "-Wconf:cat=unused:info"
    )
  )
