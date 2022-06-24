pipeline {
    agent any
    triggers {
        pollSCM '* * * * *'
    }
    stages {
    stage('build'){
        withMaven(maven: 'mvn') {
            sh "mvn clean package"
        }
    }
    }
}