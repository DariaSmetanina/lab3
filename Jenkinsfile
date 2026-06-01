pipeline {
    agent any
    environment {
        DOCKER_IMAGE = "dariasmetanina/spring-demo:${env.BUILD_ID}"
        DOCKER_CREDENTIALS = "dockerhub"
    }
    stages {
        stage('Build') {
            steps {
                // 2.3.1. Checkout repository
                checkout scm
                // 2.3.2. Build docker image, tag = Jenkins build ID
                script {
                    docker.build("${DOCKER_IMAGE}")
                }
            }
        }
        stage('Push') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', "${DOCKER_CREDENTIALS}") {
                        docker.image("${DOCKER_IMAGE}").push()
                    }
                }
            }
        }
    }
}
