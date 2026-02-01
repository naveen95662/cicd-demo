pipeline {
    agent any

    environment {
        DOCKERHUB_USERNAME = 'naveen9566'
        IMAGE_NAME = 'cicd-demo-app'
        IMAGE_TAG = 'latest'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Docker Build') {
            steps {
                bat '''
                docker build -t %DOCKERHUB_USERNAME%/%IMAGE_NAME%:%IMAGE_TAG% .
                '''
            }
        }

        stage('Docker Login') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    bat '''
                    echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
                    '''
                }
            }
        }

        stage('Docker Push') {
            steps {
                bat '''
                docker push %DOCKERHUB_USERNAME%/%IMAGE_NAME%:%IMAGE_TAG%
                '''
            }
        }

        stage('Run Container') {
            steps {
                bat '''
                docker rm -f cicd-demo-container || exit 0
                docker run -d -p 8081:8080 --name cicd-demo-container %DOCKERHUB_USERNAME%/%IMAGE_NAME%:%IMAGE_TAG%
                '''
            }
        }
    }

    post {
        success {
            echo 'Docker image pushed to Docker Hub successfully'
        }
        failure {
            echo 'Pipeline failed'
        }
        always {
            bat 'docker logout'
        }
    }
}
