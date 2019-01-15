pipeline
{
    agent {
        kubernetes {
           label "shared-lib-${UUID.randomUUID().toString()}"
           yaml """
apiVersion: v1
kind: Pod
metadata:
  labels:
    jenkins: jenkins-pipeline
spec:
  containers:
  - name: jnlp
    image: registry-proxy.lbg.eu-gb.mybluemix.net/mm-mlp/jnlp-slave:3.23-1-alpine
    ttyEnabled: true
<<<<<<< HEAD
"""
            }
    }
=======
  - name: gradle
    image: registry-proxy.lbg.eu-gb.mybluemix.net/modelmaker/gradle-builder:latest
    command:
    - cat
    tty: true
  securityContext:
    runAsUser: 0
"""
            }
    }
    environment{
        GRADLE_OPTS = "-Dorg.gradle.daemon=false"
    }
>>>>>>> 3a3457a61fd7ff23e5414874f3084883efdbe019

    stages {
        stage('Test the sharedLib') {
            steps{
                container('gradle'){
                    sh "./gradlew test"
                }
            }
        }
        stage('Run Lint on the sharedLib') {
            steps{
                container('gradle'){
                    sh "./gradlew check"
                }
            }
        }
        stage('Run Clover code coverage'){
            steps{
                container('gradle'){
                    sh "./gradlew cloverGenerateReport"
                }
            }
        }
    }

    post {
        success {
            // publish unit-test report
            publishHTML target: [
                allowMissing: false,
                alwaysLinkToLastBuild: false,
                keepAll: true,
                reportDir: 'build/reports/tests/test/',
                reportFiles: 'index.html',
                reportName: 'Unit Test Report'
            ]

            // publish Linting report
            publishHTML target: [
                allowMissing: false,
                alwaysLinkToLastBuild: false,
                keepAll: true,
                reportDir: 'build/reports/codenarc/',
                reportFiles: 'main.html',
                reportName: 'Codenarc Lint Report'
            ]

            // publish Code coverage
            step([
                $class              : "CloverPublisher",
                cloverReportDir     : "build/reports/clover",
                cloverReportFileName: "clover.xml"
            ])
            
            // Generate groovydoc
            sh "./gradlew groovydoc"

            // Publish groovydoc
            publishHTML target: [
                allowMissing: false,
                alwaysLinkToLastBuild: false,
                keepAll: true,
                reportDir: 'build/docs/groovydoc',
                reportFiles: 'index.html',
                reportName: 'Generated GroovyDoc'
            ]
        }
    }
}
