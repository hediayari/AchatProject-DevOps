pipeline {
    agent any

    stages {
        stage('Clone') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: 'oubaid']], userRemoteConfigs: [[url: 'https://github.com/hediayari/AchatProject-DevOps.git']]])
            }
        }

        stage('Clean Maven and Build') {
            steps {
                    sh 'mvn clean'
                    sh 'mvn package'
            }
        }
    }
}
