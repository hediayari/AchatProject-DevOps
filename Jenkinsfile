pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = 'oubaid-app:latest'
        DOCKER_HUB_USERNAME ='oubaidhl' 
        DOCKER_HUB_PASSWORD = '20271489ABed?!'
    }
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
                    def javaHome = tool name: 'JAVA_HOME', type: 'hudson.model.JDK'

                    withEnv(["JAVA_HOME=${javaHome}", "SONAR_HOST_URL=http://192.168.1.160:9000"]) {
                        withSonarQubeEnv('SonarQube') {
                            sh """
                                ${scannerHome}/bin/sonar-scanner \
                                -Dsonar.login=admin \
                                -Dsonar.password=vagrant \
                                -Dsonar.projectKey=1st_Sonar \
                                -Dsonar.java.binaries=target/classes
                            """
                        }
                    }
                }
            }
        }

        stage('Build and Deploy to Nexus') {
            steps {
                script {
                    sh "mvn deploy -DskipTests=true"
                }
            }
        }

                stage('Build Docker Image') {
            steps {
                script {
                   sh " docker build -t ${DOCKER_IMAGE_NAME} ."
                }
            }
        }


        stage('Push Docker Image') {
            steps {
                    sh "docker login -u $DOCKER_HUB_USERNAME -p $DOCKER_HUB_PASSWORD"
                    sh "docker tag ${DOCKER_IMAGE_NAME} oubaidhl/devops:latest"
                    sh "docker push oubaidhl/devops:latest"
            }
        }

        stage('Remove Docker Compose Containers') {
            steps {
                sh 'docker-compose down'
            }
        }

        stage('Start Docker Compose') {
            steps {
                sh 'docker-compose up -d'
            }
        }
    }
}
