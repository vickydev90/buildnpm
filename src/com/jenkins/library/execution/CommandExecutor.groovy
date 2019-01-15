package com.jenkins.library.execution

<<<<<<< HEAD
class CommandExecutor {
	static void execute(String command){
	try{
		switch(getOS()){
			case "unix" :	
				sh "${command}"
				break;
			case "windows" :
=======
def execute(String command){
try{
	withMaven() {
		switch(getOS()){
			case "unix" :
				sh "${command}"
				break;
			case "windows":
>>>>>>> 3a3457a61fd7ff23e5414874f3084883efdbe019
				bat "${command}"
				break;
			case "macos" :
				sh "${command}"
			}
<<<<<<< HEAD
		} catch(groovy.lang.MissingMethodException err) {
			if (err.getMessage().contains("com.jenkins.pipeline.execution.ExecuteCommand.sh") || err.getMessage().contains("com.jenkins.pipeline.execution.ExecuteCommand.bat")) {
				println  "Exception caught because on jenkins dependency on :" + getOS() 
			} else {
				throw err
			}
		} catch(Exception err ) {
			if(err.getMessage().contains("com.jenkins.pipeline.execution.ExecuteCommand.bat()")){
				println err.getMessage()
				println "Expected catch for test cases running on windows"
			} else {
				throw err
			}
		}
	}

	private static String getOS(){
		String osName = System.getProperty("os.name").toLowerCase();

		if (osName.contains("linux")) {
			return ("unix");
		} else if (osName.contains("mac os x") || osName.contains("darwin") || osName.contains("osx")) {
			return ("macos");
		} else if (osName.contains("windows")) {
			return ("windows");
		} else if (osName.contains("sunos") || osName.contains("solaris")) {
			return ("solaris");
		} else if (osName.contains("freebsd")) {
			return ("freebsd");
		}
=======
		}
	} catch(groovy.lang.MissingMethodException err) {
		if (err.getMessage().contains("com.jenkins.pipeline.execution.ExecuteCommand.sh") || err.getMessage().contains("com.jenkins.pipeline.execution.ExecuteCommand.bat")) {
			println  "Exception caught because on jenkins dependency on :" + getOS() 
		} else {
			throw err
		}
	} catch(Exception err ) {
		if(err.getMessage().contains("com.jenkins.pipeline.execution.ExecuteCommand.bat()")){
			println err.getMessage()
			println "Expected catch for test cases running on windows"
		} else {
			throw err
		}
	}
}

def getOS(){
	String osName = System.getProperty("os.name").toLowerCase();

	if (osName.contains("linux")) {
		return ("unix");
	} else if (osName.contains("mac os x") || osName.contains("darwin") || osName.contains("osx")) {
		return ("macos");
	} else if (osName.contains("windows")) {
		return ("windows");
	} else if (osName.contains("sunos") || osName.contains("solaris")) {
		return ("solaris");
	} else if (osName.contains("freebsd")) {
		return ("freebsd");
>>>>>>> 3a3457a61fd7ff23e5414874f3084883efdbe019
	}
}