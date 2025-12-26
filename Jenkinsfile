pipeline {
    agent any

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

        // stage('Archive WAR') {
        //     steps {
        //         archiveArtifacts artifacts: 'target\\*.war', fingerprint: true
        //     }
        // }
    }

    post {
        success {
            echo 'WAR file built successfully'
        }
        failure {
            echo 'Build failed'
        }
    }
}
