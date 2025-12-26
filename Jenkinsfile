pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    environment {
        ARTIFACTORY_SERVER = 'jfrog-api-key'
        ARTIFACTORY_REPO   = 'libs-snapshot-local'
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
                    bat 'mvn sonar:sonar -Dsonar.projectKey=SpringBoot'
                }
            }
        }

        stage('Upload WAR to JFrog') {
            steps {
                rtUpload(
                    serverId: "${ARTIFACTORY_SERVER}",
                    spec: """{
                      "files": [
                        {
                          "pattern": "target/*.war",
                          "target": "${ARTIFACTORY_REPO}/springboot/"
                        }
                      ]
                    }"""
                )
            }
        }
    }

    post {
        success {
            echo 'WAR successfully uploaded to JFrog Artifactory'
        }
        failure {
            echo 'Pipeline FAILED'
        }
    }
}
