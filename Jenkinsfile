pipeline {
    agent { docker { image 'maven:3.3.3' } }
    triggers {
        pollSCM '* * * * *'
    }
    stages {
           stage('log version info') {
         steps {
           sh 'mvn --version'
           sh 'mvn clean install'
         }
       }
     }
   }