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
            def javaHome = tool name: 'JAVA_HOME', type: 'hudson.model.JDK'

            withEnv(["JAVA_HOME=${javaHome}"]) {
                withSonarQubeEnv('SonarQube') {
                    sh """
                        ${scannerHome}/bin/sonar-scanner \
                        -Dsonar.login=admin \
                        -Dsonar.password=vagrant \
                        -Dsonar.projectKey=1stPip \
                        -Dsonar.host.url=http://192.168.1.160:9000
                    """
                }
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
