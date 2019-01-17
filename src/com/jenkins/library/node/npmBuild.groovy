package com.jenkins.library.node
import com.jenkins.library.execution.CommandExecutor
import com.jenkins.library.envVar

def npmRun(Map configFile, String runexe) {
	println "Initialting build"

	def preparedCommand = prepareCommand(configFile, runexe)
	new CommandExecutor().execute(preparedCommand)
}

def prepareCommand(Map configFile, String runexe){
	try{
		this.env()
		def buildGoals
		// Default build goals are 'install'
		if ( runexe == 'npm' ) {
			buildGoals = configFile.node.npm_goals ? configFile.node.npm_goals.toString() : "install"
		}
		else if ( runexe == 'npm run' )  {
		    buildGoals = configFile.node.npmRun_goals ? configFile.node.npmRun_goals.toString() : "build"                              
		}

		println "Creating Command for execution ...."
		def command = runexe
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

def env() {
    def variables = new envVar()
    variables.VariablesName()
}