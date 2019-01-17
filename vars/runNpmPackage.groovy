package com.jenkins.library
import com.jenkins.library.node.npmBuild
import org.yaml.snakeyaml.Yaml

def call(Map config) {
	String configPath = config.configuration ? configFile.configuration : "${env.WORKSPACE}/pipelines/conf/build-nodejs.yaml"

	Map configFile = readYaml file: configPath

    def builder = new npmPackage()
    builder.package(configFile)
}