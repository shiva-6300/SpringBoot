pipeline {
    agent any

    tools {
        maven 'Maven3'      // Jenkins → Manage Jenkins → Tools
    }

    environment {
        SONARQUBE_ENV = 'SonarQube'   // SonarQube server name in Jenkins
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
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv("${SONARQUBE_ENV}") {
                    bat '''
                    mvn sonar:sonar ^
                    -Dsonar.projectKey=SpringBoot ^
                    -Dsonar.projectName=SpringBoot ^
                    -Dsonar.host.url=http://localhost:9000
                    '''
                }
            }
        }

        // stage('Archive WAR') {
        //     steps {
        //         archiveArtifacts artifacts: 'target\\*.war', fingerprint: true
        //     }
        // }
    }

    post {
        success {
            echo 'Build + SonarQube analysis completed successfully'
        }
        failure {
            echo 'Build or SonarQube analysis failed'
        }
    }
}
