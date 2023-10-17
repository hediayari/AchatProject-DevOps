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
            def scannerHome = tool name: 'SonarQube', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
            def javaHome = tool name: 'JAVA_HOME', type: 'hudson.model.JDK'

            withEnv(["PATH+JAVA=${javaHome}/bin:${env.PATH}"]) {
                withSonarQubeEnv('SonarQube') {
                    sh """
                        ${scannerHome}/bin/sonar-scanner \
                        -Dsonar.login=admin \
                        -Dsonar.password=vagrant \
                        -Dsonar.projectKey=1st_Sonar \
                        -Dsonar.java.binaries=${javaHome}/bin/java
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
