package com.jenkins.library
import com.jenkins.library.node.npmBuild
import org.yaml.snakeyaml.Yaml


// Defaults scenario, nothing passed
def call() {
	String configPath = "${env.WORKSPACE}/pipelines/conf/java-maven-build.yaml"

	Map configFile = readYaml file: configPath

    def builder = new npmBuild()
    builder.npmRun(configFile)
}

def call(Map config) {
	String configPath = config.configuration ? configFile.configuration : "${env.WORKSPACE}/pipelines/conf/build-nodejs.yaml"

	Map configFile = readYaml file: configPath

    def builder = new npmBuild()
    builder.npmRun(configFile)
}