package com.jenkins.library.java

import spock.lang.*
import org.yaml.snakeyaml.Yaml

class JavaMavenBuilderTest extends Specification {

    def 'test keys in configuration file' (){
        given:
        
        String configuration = 'test/resources/job-configuration.yaml'
        Yaml parser = new Yaml()
    	
        
        when:
        Map configurationFile = parser.load((configuration as File).text)

        then:
        println "Check if  key maven  exists"
        assert configurationFile['maven']
        println "Check if  key build_goals  exists"
        assert configurationFile.maven.build_goals
    }

<<<<<<< HEAD
    def 'test runJavaMavenBuild function' (){
        given:
            String configuration = 'test/resources/job-configuration.yaml'
            Yaml parser = new Yaml()
            Map configurationFile = parser.load((configuration as File).text)
            Map deciders = [archType: 'java'  , buildTool : 'maven']
        
        when:
            def builder = new JavaMavenBuilder()
            def output = builder.runJavaMavenBuild(configurationFile)
        
        then:
           assert output == "maven"
   
    }

    def 'test runJavaMavenBuild function 2' () {
        println "Testing the function runJavaMavenBuild"
                
        given: "the configuration file is present and loaded successfully " 
            String configuration = 'test/resources/job-configuration.yaml'
            Yaml parser = new Yaml()
            Map configurationFile = parser.load((configuration as File).text)
            Map deciders = [archType: 'java'  , buildTool : 'maven']
        
		when: "the variables from the configuration files are retrieved successfully "
            def builder = new JavaMavenBuilder()
            def output = builder.runJavaMavenBuild(configurationFile)
        
        then: "Compare the variables "
            assert "mvn " + configurationFile['bdd']['maven']['bdd_goals'] == output
    }   
=======
// Disabled temporarily
    // def 'test runJavaMavenBuild function' (){
    //     given:
    //         String configuration = 'test/resources/job-configuration.yaml'
    //         Yaml parser = new Yaml()
    //         Map configurationFile = parser.load((configuration as File).text)
    //         Map deciders = [archType: 'java'  , buildTool : 'maven']
        
    //     when:
    //         def builder = new JavaMavenBuilder()
    //         def output = builder.runJavaMavenBuild(configurationFile)
        
    //     then:
    //        assert output == "maven"
   
    // }

    // def 'test runJavaMavenBuild function 2' () {
    //     println "Testing the function runJavaMavenBuild"
                
    //     given: "the configuration file is present and loaded successfully " 
    //         String configuration = 'test/resources/job-configuration.yaml'
    //         Yaml parser = new Yaml()
    //         Map configurationFile = parser.load((configuration as File).text)
    //         Map deciders = [archType: 'java'  , buildTool : 'maven']
        
	// 	when: "the variables from the configuration files are retrieved successfully "
    //         def builder = new JavaMavenBuilder()
    //         def output = builder.runJavaMavenBuild(configurationFile)
        
    //     then: "Compare the variables "
    //         assert "mvn " + configurationFile['bdd']['maven']['bdd_goals'] == output
    // }   
>>>>>>> 3a3457a61fd7ff23e5414874f3084883efdbe019
}