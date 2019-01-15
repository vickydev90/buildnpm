package com.jenkins.library.execution

def execute(String command){
try{
	 //withMaven() {
		switch(getOS()){
			case "unix" :
				sh "${command}"
				break;
			case "windows":
				bat "${command}"
				break;
			case "macos" :
				sh "${command}"
			}
		//}
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
	}
}