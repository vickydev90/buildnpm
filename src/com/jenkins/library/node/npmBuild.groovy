package com.jenkins.library.node
import com.jenkins.library.execution.CommandExecutor

def npmRun(Map configFile) {
	println "Initialting build"

	def preparedCommand = prepareCommand(configFile)
	new CommandExecutor().execute(preparedCommand)
	
//	def NpmRun = NpmRun(configFile)
//	new CommandExecutor().execute(NpmRun)
}

def prepareCommand(Map configFile){
	try{
		// Default build goals are 'install'
		def buildGoals = configFile.node.npm_goals ? configFile.node.npm_goals.toString() : "install"

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

/* def NpmRun(Map configFile){
	try{
		// Default build goals are 'clean package'
		def buildGoals = configFile.node.npmRun_goals ? configFile.node.npmRun_goals.toString() : "release"

		println "Creating Command for execution ...."
		def command = "npm run"
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
} */