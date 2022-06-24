pipeline {
    agent any
    tools {
            maven 'Maven 3.8.6'
            jdk 'jdk11'
    }
    triggers {
        pollSCM '* * * * *'
    }
    stages {
            stage ('Initialize') {
                steps {
                    sh '''
                        echo "PATH = ${PATH}"
                        echo "M2_HOME = ${M2_HOME}"
                    '''
                }
            }
             stage('Build') {
             steps {
               sh 'mvn --version'
               sh 'mvn clean install'
             }
           }
     }
   }