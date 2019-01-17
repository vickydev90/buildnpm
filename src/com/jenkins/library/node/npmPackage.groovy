package com.jenkins.library.node
import com.jenkins.library.execution.CommandExecutor

def tarfunc(Map configFile, String targetEnv) {
	if (targetEnv == "integration-branch") {
	println "building tar..."
	
	def nexusURL = configFile.nexus.url ? configFile.nexus.url.toString() : "null"
	def credentialsID = configFile.nexus.credentials ? configFile.nexus.credentials.toString() : "null"
	def appName = configFile.application ? configFile.application.toString() : "null"
	def packageVersion = configFile.packageVersion ? configFile.packageVersion.toString() : "null"
	String artifact = "${appName}-artifact-${packageVersion}.tar.gz"
	
	try {
    dir('j2') {
      deleteDir()
      unstash "artifact-${appName}-${targetEnv}"
      artifact = sh(returnStdout: true, script: 'ls *.tar.gz | head -1').trim()
      withCredentials([
       usernameColonPassword(    credentialsId: credentialsID,
       variable: 'NEXUS_CREDS')
   ]) { sh     """curl --insecure    -sS \
                           -u $NEXUS_CREDS \
                           --upload-file \
                           ${artifact} \
                           ${nexusURL}/${artifact} 
               """  
   }
      
        }
  } catch (Exception ex) {
		println "FAILED: export ${ex.message}"
		throw ex
	} finally {
		// step([$class: 'WsCleanup', notFailBuild: true])
  		}
	}
}