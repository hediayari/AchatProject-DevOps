pipeline {
    agent any
    tools{
        maven 'maven-3.9.4'
        // dockerTool 'docker'
    }
    environment{
        DOCKERHUB_CREDENTIALS=credentials('docker-hub-neysho')
        // BUILD_NUMBER = "${env.BUILD_NUMBER-SPRINGBOOT-SAMPLE}"
    }

    stages{
        stage('checkout'){
                        steps{
                        //  deleteDir()
                         checkout scmGit(branches: [[name: '*/aziz']], extensions: [], userRemoteConfigs: [[credentialsId: 'github-neysho', url: 'https://github.com/hediayari/AchatProject-DevOps.git']])
                       }
                  }
            stage('Package Maven'){
                steps{
                    sh 'mvn clean package'
                }
                post {  
                    failure {
                            slackSend color: "danger", 
                             message: "Pipeline failed in stage 'Package Maven'",
                             tokenCredentialId: 'slack-alert-bot'
                     }
                }
            }

            stage('SonarQube'){
                steps {
                 script {
                     withSonarQubeEnv(credentialsId: 'sonar-id') {
                         sh "mvn sonar:sonar"
                     }
                 }
             }
             post {  
                    failure {
                            slackSend color: "danger", 
                             message: "Pipeline failed in stage 'SonarQube'",
                             tokenCredentialId: 'slack-alert-bot'
                     }
                }
            }

            stage('Test'){
                steps{
                    sh 'mvn test'
                }
                post {  
                    failure {
                            slackSend color: "danger", 
                             message: "Pipeline failed at the Testing stage",
                             tokenCredentialId: 'slack-alert-bot'
                     }
                }
            }

            stage('Nexus'){
                steps {
                nexusArtifactUploader artifacts:
                 [[artifactId: 'achat',
                  classifier: '',
                  file: 'target/achat-app.jar',
                  type: 'jar']],
                  credentialsId: 'nexus', groupId: 'tn.esprit.rh',
                  nexusUrl: '192.168.1.100:8081',
                   nexusVersion: 'nexus3', protocol: 'http',
                    repository: 'achat-app',
                    version: '1.0'
             }
             post {  
                    failure {
                            slackSend color: "danger", 
                             message: "Pipeline failed in stage 'Nexus'",
                             tokenCredentialId: 'slack-alert-bot'
                     }
                }
            }
             stage('docker build'){
                 steps{
                         sh ''' ls
                                docker build -t neysho/achat-backend:1 .
                         '''
               }
               post {  
                    failure {
                            slackSend color: "danger", 
                             message: "Pipeline failed in stage 'Docker Build'",
                             tokenCredentialId: 'slack-alert-bot'
                     }
                }
             }
             stage('docker push'){
                steps{
                    // sh ''' echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin
                    //             docker push neysho/achat-backend:1
                    // '''
                    sh 'ls'
                }
                post {  
                    failure {
                            slackSend color: "danger", 
                             message: "Pipeline failed in stage 'Docker Push'",
                             tokenCredentialId: 'slack-alert-bot'
                     }
                }
             }
             stage('Docker compose'){
                steps{
                    sh 'docker compose up -d'
                }
                post {  
                    failure {
                            slackSend color: "danger", 
                             message: "Pipeline failed in stage 'Docker compose'",
                             tokenCredentialId: 'slack-alert-bot'
                     }
                }
            }
            stage('Trivy Scan'){
                steps{
                    sh 'trivy image --scanners vuln neysho/achat-backend:1  --timeout 35m > backend-scan.txt'
                }
                post { 
                    success {
                     slackUploadFile filePath: 'backend-scan.txt', initialComment: 'Trivy Scan :'
                     } 
                    failure {
                            slackSend color: "danger", 
                             message: "Pipeline failed in stage 'Trivy Scan'",
                             tokenCredentialId: 'slack-alert-bot'
                     }
                }
            }

    }

    post {
            always {
                script {
                    emailext attachLog: true, body: 'Here is your Log file.',
                    compressLog: true, subject: 'Jenkins Notification',
                    attachmentsPattern: 'backend-scan.txt', to: 'azizamari100@gmail.com'
                    cleanWs()
                }
              }
               success {
                    slackSend color: "good",  message: 'Pipeline completed successfully!',
                     tokenCredentialId: 'slack-alert-bot'
              }  
               failure {
                    slackSend color: "warning",
                     message: 'Check logs.',
                     tokenCredentialId: 'slack-alert-bot'
             }
         }

}
