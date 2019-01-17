package com.jenkins.library
import com.jenkins.library.node.npmBuild
import org.yaml.snakeyaml.Yaml


// Defaults scenario, nothing passed
def call() {
	String configPath = "${env.WORKSPACE}/pipelines/conf/build-nodejs.yaml"

	Map configFile = readYaml file: configPath

    def builder = new npmBuild()
    builder.npmRun(configFile)
}

def call(String runexe) {
	
	String configPath = "${env.WORKSPACE}/pipelines/conf/build-nodejs.yaml"
	Map configFile = readYaml file: configPath
	println "runexe: ${runexe}"
	//String configPath = config.configuration ? configFile.configuration : "${env.WORKSPACE}/pipelines/conf/build-nodejs.yaml"
	
    def builder = new npmBuild()
    
    builder.npmRun(configFile, runexe)
}
