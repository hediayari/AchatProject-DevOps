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

        
        stage('SonarQube analysis') {
            steps {
                script {
                    def projectDir = sh(script: 'pwd', returnStatus: true).trim()
                    def mvnwScript = "${projectDir}/mvnw"

                    sh "${mvnwScript} clean org.jacoco:jacoco-maven-plugin:prepare-agent install -Dmaven.test.failure.ignore=true"
                    sh "${mvnwScript} sonar:sonar \
                        -D sonar.login=admin \
                        -D sonar.password=vagrant \
                        -D sonar.projectKey=1stPip \
                        -D sonar.host.url='http://192.168.1.160:9000'"
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
