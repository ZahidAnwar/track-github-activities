pipeline {
    agent any
    tools {
            maven 'maven-3.6.3'
    }
    triggers {
        pollSCM '* * * * *'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}