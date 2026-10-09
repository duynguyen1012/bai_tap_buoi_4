pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                echo 'Cloning / Checking out source code from GitHub...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building application...'
                // Lenh thuc thi tren Windows (vi du: bat 'mvn clean compile' hoac bat 'echo Build Success')
                bat 'echo Build completed successfully!'
            }
        }

        stage('Test') {
            steps {
                echo 'Running automated tests...'
                bat 'echo All tests passed!'
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution finished.'
        }
        success {
            echo 'CI/CD Pipeline Build SUCCESS!'
        }
        failure {
            echo 'CI/CD Pipeline Build FAILED!'
        }
    }
}
