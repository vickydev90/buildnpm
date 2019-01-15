package com.jenkins.library.execution

import spock.lang.*
import org.yaml.snakeyaml.Yaml

class CommandExecutorTest extends Specification {
    def 'test getOS function' (){
        given:
			String osName = System.getProperty("os.name").toLowerCase();
			
			if (osName.contains("linux")) {
				osName = "unix" 
			} else if (osName.contains("mac os x") || osName.contains("darwin") || osName.contains("osx")) {
				osName ="macos"
			} else if (osName.contains("windows")) {
				osName ="windows"
			} else if (osName.contains("sunos") || osName.contains("solaris")) {
				osName ="solaris"
			} else if (osName.contains("freebsd")) {
				osName ="freebsd"
			}
              
        when:
			def exec = new CommandExecutor()
			def output = exec.getOS()

        then:
        	assert osName ==  output
    }

}