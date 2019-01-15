package com.jenkins.library.node
import com.jenkins.library.execution.CommandExecutor

def npmRun(Map configFile) {
	println "Initialting build"

	def preparedCommand = prepareCommand(configFile)
	new CommandExecutor().execute(preparedCommand)
}

def prepareCommand(Map configFile){
	try{
		// Default build goals are 'clean package'
		def buildGoals = configFile.node.npm_goals ? configFile.maven.build_goals.toString() : "install"

		println "Creating Command for execution ...."
		def command = "npm"
		def space = " "

		// append build goals
		command = command + space + buildGoals
		println "command is : " + command
		return command
	} catch (groovy.lang.MissingMethodException error) {
		println "Exception Expected "
		throw error
	} catch (Exception ex) {
		println "FAILURE: runNpm(): ${ex.message}"
		throw ex
	}
	
}