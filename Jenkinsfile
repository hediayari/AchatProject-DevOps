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

stage('SonarQube Analysis') {
            steps {
                script {
                    def sonarUrl = 'http://192.168.1.160:9000'
                    def projectName = '1stPip'
                    def sonarUsername = 'admin'
                    def sonarPassword = 'vagrant'

                    sh "sonar-scanner -Dsonar.projectKey=${projectName} -Dsonar.sources=src -Dsonar.host.url=${sonarUrl} -Dsonar.login=${sonarUsername} -Dsonar.password=${sonarPassword}"
                }
            }
        }
    }

 //   post {
  //      success {
            // Additional post-build actions on success
   //     }
   // }
}
