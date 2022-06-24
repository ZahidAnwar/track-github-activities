pipeline {
    agent any
    triggers {
        pollSCM '* * * * *'
    }
    stages {
           stage('Build') {
         steps {
           sh 'mvn --version'
           sh 'mvn clean install'
         }
       }
     }
   }