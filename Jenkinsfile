pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    environment {
        ARTIFACTORY_SERVER = 'jfrog-jenkins-token'     
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
                script {
                    def server = Artifactory.server(env.ARTIFACTORY_SERVER)

                    def uploadSpec = """
                    {
                      "files": [
                        {
                          "pattern": "target/*.war",
                          "target": "${ARTIFACTORY_REPO}/springboot/"
                        }
                      ]
                    }
                    """
                    server.upload(uploadSpec)
                }
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
