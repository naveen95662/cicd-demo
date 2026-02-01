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
                sh 'mvn clean package'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t cicd-demo-app .'
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                docker rm -f cicd-demo || true
                docker run -d -p 8081:8080 --name cicd-demo cicd-demo-app
                '''
            }
        }
    }
}
