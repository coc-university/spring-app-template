# Maven

We encourage to use Maven for Java Projects in Codecamp.
You can find Expertise in the Digital Transformation and the Evo-X Team.

<!-- TABLE OF CONTENTS -->
<ul>
  <li>
    <a href="#Recommendation">Recommendation</a>
  </li>
  <li>
    <a href="#paketmanager">Paketmanager</a>
  </li>
  <li>
    <a href="#repository">Repository</a>
  </li>
  <li>
    <a href="#artifactory">Artifactory</a>
  </li>
  <li>
    <a href="#pom-sructure">Pom Structure</a>
  </li>
  <li>
    <a href="#lifecycles">Lifecycles</a>
    <ul>
        <li><a href="#phase-vs-goals">Phase vs Goals</a></li>
        <li><a href="#validate-vs-compile-vs-package">validate vs compile vs package</a></li>
        <li><a href="#test-vs-verify">test vs verify</a></li>
      </ul>
  </li>
  <li>
    <a href="#plugins">Plugins</a>
    <ul>
        <li><a href="#plugin-hookup">Plugin hookup</a></li>
      </ul>
  </li>
  <li>
    <a href="#helpful-commands">Helpful commands</a>
  </li>
  <li>
    <a href="#side-nodes">Side nodes</a>
    <ul>
        <li><a href="#dependency-mediation">Dependency mediation</a></li>
        <li><a href="#default-maven-group">Default maven group</a></li>
        <li><a href="#test-vs-verify">test vs verify</a></li>
      </ul>
  </li>
  <li>
    <a href="#checkout">Checkout</a>
  </li>
</ul>

## Paketmanager

Goals of a package-manager:

- dependency orchistration
- build (compile, validate)
- deploy/publish

=> produces manifest that leads to the same result independently of the platform, i.e. merges the source code.

## Repository

- publishing Artifacts:

  - GAV (Group, ArtifactId, Version)
  - Metadata, Build-info etc.
  - Artifacts are to all available, who have access to repository

- Common Repositories
  - central
  - libs-snapshots
  - libs-releases
  - archetypes-local
  - plugins-releases
  - plugins-snapshots
  - .m2
- mvncentral, ghrp, npm-registry ...

## Pom Structure

- [POM Convention](https://maven.apache.org/developers/conventions/code.html#POM_Code_Convention)

- scm, head tag (maven-release-plugin which on the one hand automatically adjusts the version in the POM and performs a commit for these changes, and on the other hand
  changes and tags the project accordingly)
- [distributionManagement](https://maven.apache.org/pom.html#Distribution_Management) (In the distributionMangement you configure where the final artifacts should be stored.
  In our case the artifacts (jar files, etc.) go into the Artifactory).
  - Here you have to make sure that the <id> of the repository or snapshotRepository matches the server ID in the Maven
    settings.xml (Maven Authentication Configuration plays here also a role).
- dependencyManagement: allows to set versions of dependencies on the top level (BOM, parent-pom).
  - sub-modules which inherit from it can include dependencies without explicit version.
  - dependencies which are used in several sub-modules should be defined via dependencyManagement.
- overloaded preoperties/versions
  - sub-modules can override parent-properties (to prevent this: hard-code versions in parent
    hard-code; [enforcer](http://maven.apache.org/enforcer/enforcer-rules/requireProperty.html) nurzen)
    - example: [Springboot-Starter Versions](https://docs.spring.io/spring-boot/docs/current/reference/html/dependency-versions.html#appendix.dependency-versions.properties)
- optional:
  - lombok als _optional_ oder _provided_ um dependency size zu minimieren

## Lifecycles

### Phase vs Goals

A phase: clean, compile..

A goal: sonar:sonar

Note different syntax.

> A phase is build of goals, but a goal is not bound to any build phase could be executed outside of the build lifecycle
> by direct invocation. [Maven](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)

> A Lifecycle is a sequence of named phases. Phases execute sequentially. Executing a phase means executes all previous
> phases. [jstobigdata](https://jstobigdata.com/maven/maven-build-lifecycle-effective-pom/)

### validate vs compile vs package

- _validate_ the project is in a correct state, and all related information is available
- _compile_ transfers from \*.java -> \*.class
- _package_ bundles the class, to be ready to ship. (e.g. move the .war to an application-server)

### test vs verify

- _mvn test_ runs the UnitTests on compiled test resources. We use the surefire plugin to generate unit-test results.
- _mvn verify_ runs th Integrationtests on the packaged/deployed unit. We use failsafe plugin to generate rest results.

## Plugins

Plugins help build your bundle, provide additional information, and customize the phases.
They can be executed seperately since they run a specific _goal_.
Phases are bind to specific plugin goals,
see [Default Lifecycle Bindings](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html).

### Plugin hookup

- Plugins usually have a default phase
  hookup (`defaultPhase = LifecyclePhase.VALIDATE` [JFrog maven plugin](https://github.com/jfrog/artifactory-maven-plugin/blob/master/src/main/java/org/jfrog/buildinfo/ArtifactoryMojo.java#L35))
  .
- one can override in which phase the plugin is being executed, by specifying the `<phase>` in the `execution blog`:

```xml

<groupId>org.jfrog.buildinfo</groupId>
<artifactId>artifactory-maven-plugin</artifactId>
<version>${version.artifactory-plugin}</version>
<inherited>false</inherited>
<executions>
  <execution>
      <id>build-info</id>
      <phase>package</phase>
      <goals>
          <goal>publish</goal>
      </goals>
      ...
  </execution>
</executions>
```

## Helpful commands

Dependency Tree

```shell
mvn dependency:tree
```

Analyze dependencies

```shell
mvn dependency:analyze
```

Sonar locally

- prerequisite: change .m2/settings.xml and configure pom for sonar, see
  here: [Sonar-Locally](https://architektur/wiki/display/DEV/Sonar+Code+Analyse+mit+Maven)

```sh
mvn clean install sonar:sonar
```

Running all IT Tests (due
to [failsafe-plguin](https://maven.apache.org/surefire/maven-failsafe-plugin/examples/single-test.html))

```sh
mvn -Dit.test=*IT verify
```

See the 'merged' pom by running:

```sh
mvn help:effective-pom
```

## Side nodes

### Dependency mediation

"nearest definition" means that the version used will be the closest one to your project in the tree of dependencies.

```
  A
  ├── B
  │   └── C
  │       └── D 2.0
  └── E
      └── D 1.0
```

==> D 1.0 is being used.
In order to use D 2.0 declare it!

```
  A
  ├── B
  │   └── C
  │       └── D 2.0
  ├── E
  │   └── D 1.0
  │
  └── D 2.0
```

see [Maven-Enforcer](https://maven.apache.org/enforcer/enforcer-rules/dependencyConvergence.html), or run

```sh
mvn validate
```

- _exclusions_ - explicitly exclude transitive dependencies if you want provide own

### Default maven group

```xml

<groupId>org.apache.maven.plugins</groupId>
<artifactId>maven-compiler-plugin</artifactId>
```

the groupid can be removed, since: "org.apache.maven.plugins is the designated groupId for all Maven plugins." (
see [Maven](https://maven.apache.org/guides/getting-started/))

```xml

<artifactId>maven-compiler-plugin</artifactId>
```

## Checkout:

- [Configure-Plugins](https://maven.apache.org/guides/mini/guide-configuring-plugins.html)
- [Maven-Enforcer](https://maven.apache.org/enforcer/maven-enforcer-plugin/usage.html)
- [Maven-Quiz](https://andresalmiray.com/maven-dependencies-pop-quiz-results/)

[//]: # "## Advanced"
[//]: # "### Dockerimages in Maven"
[//]: # "### NPM in Maven"
