
pipeline {
    agent any

    stages {
        stage('Verify Jenkins') {
            steps {
                echo 'Jenkins successfully checked out the Student CRUD project!'
                bat 'java -version'
            }
        }

        stage('Build and Test') {
            steps {
                withCredentials([string(credentialsId: 'db-password', variable: 'DB_PASSWORD')]) {
                    bat 'mvnw.cmd clean package'
                }
            }
        }
    }
}
