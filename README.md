# Build for Java projects using Maven

<<<<<<< HEAD
A [Jenkins shared library](https://jenkins.io/doc/book/pipeline/shared-libraries/) for building artifacts for Java based projects using Maven as build tool in declarative as well as scripted Jenkins Pipeline.

## Supported Programming Languages

| Programming Language | Build tool|
|---|---|
| [Java](https://www.java.com/en/) |  [Maven](https://maven.apache.org/) |

## Pre-requisites

1. Install [Pipeline+Shared+Groovy+Libraries+Plugin](https://wiki.jenkins.io/display/JENKINS/Pipeline+Shared+Groovy+Libraries+Plugin) plugin on jenkins server.
2. This shared library requires that you have the Maven and JDK already installed already on the server where this shared library will be run. If you are using Jenkins running on Kubernetes, then you need to include JDK and Maven containers in your build pipeline's pod spec.
3. The library is [correctly configured](https://jenkins.io/doc/book/pipeline/shared-libraries/#global-shared-libraries) in jenkins server.
4. A job configuration file in YAML format should be present in the source code which contains the key value pairs (details below).

## Configuring the library on Jenkins server and using it in pipeline

You need to add the Shared library at the ***Manage Jenkins > Configure system*** or within your project configuration as shown in the below image

<img src="images/jenkins-sharedlib.png" alt="alt text" width="256" height="256">

Once you have defined the shared library, you can load this library in your pipeline as indicated in the example below:

```groovy
// Load library
@Library('devops-java-maven-build_nix')

pipeline{
....
..
```

Alternatively, the library can be loaded dynamically by using below syntax, provided you have a Jenkins credential named `ghe-credentials` with proper SSH key that can access public repositories on Sandbox GitHub.

```groovy
library identifier: 'java-maven-build_nix@master', retriever: modernSCM(
  [$class: 'GitSCMSource',
   remote: 'git@git@github.lbg.eu-gb.bluemix.net:jenkins-shared-library/bdd-java-maven_multi.git',
   credentialsId: 'ghe-credentials'])
```

## Parameters Required

This jenkins shared library  expects below mandatory  parameters which will be deciding the language for which the shared library will be used.

| Parameters | Description | Default |
| --- | --------------- | -----|
| configuration  | Path to YAML configuration file which has various attributes needed for the library to function are defined. Details on which variables can be defined with default values are mentioned below. | `pipelines/conf/java-maven-build.yaml` |

## Job configuration

Below are the job configurations that be passed via configuration file `pipelines/conf/java-maven-build.yaml`.
=======
[![Build Status](https://jenkins-sharedlib.lbg.eu-gb.mybluemix.net/buildStatus/icon?job=jenkins-shared-library/java-maven-build_nix/master)](https://jenkins-sharedlib.lbg.eu-gb.mybluemix.net/job/jenkins-shared-library/job/java-maven-build_nix/job/master/)

A [Jenkins shared library](https://jenkins.io/doc/book/pipeline/shared-libraries/) for building artifacts for Java based projects using Maven as build tool.

## Supported Programming Languages

| Programming Language | Build tool|
|---|---|
| [Java](https://www.java.com/en/) |  [Maven](https://maven.apache.org/) |

## Why use this library

This library is aimed for anyone who wants to build an artifact of a Java project using Maven. It aims to trivialize the code and configuration required for the person creating the pipelines/automation for their project.

It aims to provide (at least) followig benifits versus designing the pipeline from scratch:

- Increasing the pace at which a new project can be configured for continutous delivery.
- Helps absract the code away from `Jenkinsfile` used for automation to allow it to be much more readable while still allowing strong configuration opportunities.
- Moving configuration as code, so that all (or most) of details required for the Java project to build is available in source-code management provider.
- Allows for shared development of a component across various teams and creating reusable code.

## Pre-requisites

Before using this library there are certain pre-requisites that must be fulfilled on your instance of Jenkins.

### Plugins

Install following plugins:

- [Pipeline Shared Groovy Libraries Plugin](https://wiki.jenkins.io/display/JENKINS/Pipeline+Shared+Groovy+Libraries+Plugin)
- [Maven pipeline integration plugin](https://plugins.jenkins.io/pipeline-maven/)

Read through [this page](https://jenkins.io/doc/book/managing/plugins/#from-the-web-ui) for details on how to install a plugin.

### Tools

This shared library requires that you have the Maven and JDK already installed already on the server where this shared library will be run.  
Here are articles to [installing JDK](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html) and [installing Maven](https://maven.apache.org/install.html).  

If you are using Jenkins running on Kubernetes, then you need to include JDK and Maven containers in your build pipeline's pod spec.  
An example of using a docker image as build slave can be found in [examples/Jenkinsfile](examples/Jenkinsfile). Specifically the docker image `registry-proxy.lbg.eu-gb.mybluemix.net/java-build-tools:latest` has JDK and Maven installed on it:

```groovy
pipeline
{
    agent {
        kubernetes {
          label "shared-lib-${UUID.randomUUID().toString()}"
          yaml """
apiVersion: v1
kind: Pod
metadata:
  labels:
    jenkins: jenkins-pipeline
spec:
  containers:
  - name: jnlp
    image: registry-proxy.lbg.eu-gb.mybluemix.net/mm-mlp/jnlp-slave:3.23-1-alpine
    ttyEnabled: true
  - name: java-build-tools
    image: registry-proxy.lbg.eu-gb.mybluemix.net/java-build-tools:latest
    imagePullPolicy: Always
    command:
    - cat
    tty: true
"""
}
.
.
.
```

### Shared-libraries

The library (and any pre-requisites) must be correctly configured in Jenkins before use. See [this article](https://jenkins.io/doc/book/pipeline/shared-libraries/#global-shared-libraries) to understand how to configure a shared-library in jenkins server.

### Dependencies

This shared library uses functions exposed by other shared-libraries. Therefore those libraries must be included as pre-requisite.

- Git Workflow library. It can be found at it's [GitHub repository](https://github.lbg.eu-gb.bluemix.net/jenkins-shared-library/gitflowEnablers_multi).  
   _Either configure the library as global library (process desribed above [shared-libraries](#shared-libraries)) **OR** dynamically load the library._  
   To dynamicaly load this required library just add below snippet at start of your `Jenkinsfile`.

    ```groovy
    library identifier: 'gitflowEnablers_multi@master', retriever: modernSCM([$class: 'GitSCMSource',
      remote: 'https://github.lbg.eu-gb.bluemix.net/jenkins-shared-library/gitflowEnablers_multi.git',
      credentialsId: 'jenkinsGHEPAT'])
    ```

### Using the library

_Either configure the library as global library (process desribed above [shared-libraries](#shared-libraries)) **OR** dynamically load the library._

See the example at [examples/Jenkinsfile](examples/Jenkinsfile) to see how dynamically load the library. It requiers you to know the Git repository containing this library and a Jenkins credential ID which can allow clone operation on the repository.  
Relevant snippet below:

```groovy
library identifier: 'java-maven-build_nix@master',retriever: modernSCM([$class: 'GitSCMSource',
  remote: 'https://github.lbg.eu-gb.bluemix.net/jenkins-shared-library/java-maven-build_nix.git',
  credentialsId: 'jenkinsGHEPAT'])
```

Once library is configured just use the `runMavenBuild` function exposed by library like below example or the example file at [examples/Jenkinsfile](examples/Jenkinsfile).

```groovy
stage('Build') {
  steps{
      runMavenBuild()
    }
}
```

Details on what configuration options are available for `runMavenBuild` can be [seen below](#parameters).

### Configuration

A job configuration file in YAML format should be present in the source code which contains the key value pairs ([details below](#job-configuration)).

## Parameters

This jenkins shared library  expects below parameter which is used to pass on the path of configuration file which is used to define variables shared-library to work or overriding defaults. This is not a required value

| Parameters | Description | Default |
| --- | --------------- | -----|
| configuration  | Path to YAML configuration file which has various attributes needed for the library to function are defined. Details on which variables can be defined with default values are mentioned below. | `pipelines/conf/java-maven-build.yaml` |

## Job configuration

Below are the job configurations that can be passed via configuration file `pipelines/conf/java-maven-build.yaml` to override the defaults.
>>>>>>> 3a3457a61fd7ff23e5414874f3084883efdbe019

| Parameters | Description | Default |
| --- | --------------- | -----|
| `maven.pom_path` | Location of `pom.xml` which will be used to build the project. This should be relative to repository root| `pom.xml` |
| `maven.build_goals` | Maven goals to run for building the artifact from current project | `clean package` |
| `maven.settings_file` | Location of Maven settings file | Use no settings file |
<<<<<<< HEAD
| `maven.extra_args` | An array of extra arguments to provide to Maven during build time. E.g. ignore test errors etc. | `["-DskipTests"]` |

An example can be found at `pipelines/conf/java-maven-build.yaml`
=======
| `maven.extra_args` | An array of extra arguments to provide to Maven during build time. E.g. skip test etc. | `["-DskipTests"]` |

An example can be found at `pipelines/conf/java-maven-build.yaml`
>>>>>>> 3a3457a61fd7ff23e5414874f3084883efdbe019
