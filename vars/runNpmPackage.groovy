package com.jenkins.library
import com.jenkins.library.node.npmPackage
import org.yaml.snakeyaml.Yaml

def call(String targetEnv) {
	
	String configPath = "${env.WORKSPACE}/pipelines/conf/build-nodejs.yaml"
	Map configFile = readYaml file: configPath
	
	//String configPath = config.configuration ? configFile.configuration : "${env.WORKSPACE}/pipelines/conf/build-nodejs.yaml"
	
    def archive = new npmPackage()
    
    archive.tarfunc(configFile, targetEnv)
 }