pipeline {
    agent any

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
                bat 'docker build -t cicd-demo-app .'
            }
        }

        stage('Run Container') {
            steps {
                bat '''
                docker rm -f cicd-demo-container || exit 0
                docker run -d -p 8081:8080 --name cicd-demo-container cicd-demo-app
                '''
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully'
        }
        failure {
            echo 'Pipeline failed'
        }
    }
}
