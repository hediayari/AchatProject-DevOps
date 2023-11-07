pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = 'app-back'
        DOCKER_IMAGE_VERSION = '1.0.0'
    }

    stages {

        stage('Test With JUnit And Mockito') {
            steps {
                sh 'mvn test '
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

        stage('Deploy to Nexus') {
            steps {
                script {
                    sh "mvn deploy -DskipTests=true"
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_VERSION} ."
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    sh "docker login -u \$DOCKER_HUB_USERNAME -p \$DOCKER_HUB_PASSWORD"
                    sh "docker tag ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_VERSION} \$DOCKER_HUB_USERNAME/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_VERSION}"
                    sh "docker push \$DOCKER_HUB_USERNAME/${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_VERSION}"
                }
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
        stage('Start Start Grafana & Prometheus') {
            steps {
                sh 'docker start grafana prometheus'
            }
        }
    }
}
