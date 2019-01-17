package com.jenkins.library
import com.jenkins.library.execution.CommandExecutor

def runfunction() {

	String configPath = "${env.WORKSPACE}/pipelines/conf/build-nodejs.yaml"
	Map configFile = readYaml file: configPath
	def targetEnv = "${env.branch}"
	def nexusURL = configFile.nexus.url ? configFile.nexus.url.toString() : "null"
	def credentialsID = configFile.nexus.credentials ? configFile.nexus.credentials.toString() : "null"
	def appName = configFile.application ? configFile.application.toString() : "null"
	def packageVersion = configFile.packageVersion ? configFile.packageVersion.toString() : "null"
	String artifact = "${appName}-${targetEnv}-artifact-${packageVersion}.tar.gz"
	
	  writeFile file: '/tmp/package.sh', text: libraryResource('package.sh')
	  def pack = "chmod +x /tmp/package.sh"
	  sh(returnStdout: true, script: pack)
	  sh """/tmp/package.sh ${artifact}"""
	  dir('j2') {
      stash name: "artifact-${context.application}-${targetEnv}", includes: artifact
      archiveArtifacts 	artifacts: artifact, onlyIfSuccessful: true
    }
    }
    
 