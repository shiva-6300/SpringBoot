pipeline {
    agent any
    tools {
        maven 'Maven3'
        sonarScanner 'SonarScanner'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/shiva-6300/SpringBoot.git'
            }
        }

        stage('Build WAR') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    bat '''
                    sonar-scanner ^
                    -Dsonar.projectKey=SpringBoot ^
                    -Dsonar.projectName=SpringBoot ^
                    -Dsonar.sources=src ^
                    -Dsonar.java.binaries=target
                    '''
                }
            }
        }
    }

    post {
        success {
            echo 'Build + SonarQube analysis SUCCESS'
        }
        failure {
            echo 'Build or SonarQube FAILED'
        }
    }
}
