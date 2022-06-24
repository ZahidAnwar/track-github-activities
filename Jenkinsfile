pipeline {
    agent any
    tools {
            maven 'maven-3.6.1'
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