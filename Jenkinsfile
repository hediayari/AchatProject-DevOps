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
                    def scannerHome = tool name: 'SonarQube', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
                    def scannerScript = "${scannerHome}/bin/sonar-scanner"
                    def projectName = '1stPip'
                    def sonarUrl = 'http://192.168.1.160:9000' 

                    withSonarQubeEnv('Your_SonarQube_Installation') {
                        sh "${scannerScript} -Dsonar.projectKey=${projectName} -Dsonar.sources=src -Dsonar.host.url=${sonarUrl}"
                    }
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
