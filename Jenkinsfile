pipeline {
    agent {
        docker {
            image 'maven:3.8.1-adoptopenjdk-11'
            args '-v $HOME/.m2:/root/.m2'
        }
    }
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