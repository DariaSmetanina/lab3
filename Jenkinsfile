pipeline {
    agent any
    environment {
        DOCKER_IMAGE = "dariasmetanina/spring-demo:${env.BUILD_ID}"
        DOCKER_CREDENTIALS = "dockerhub"
        KUBE_CREDENTIALS = "minikube"
    }
    stages {
        stage('Build') {
            steps {
                checkout scm
                script {
                    docker.build("${DOCKER_IMAGE}")
                }
            }
        }
        stage('Push') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', "${DOCKER_CREDENTIALS}") {
                        docker.image("${DOCKER_IMAGE}").push("latest")
                        docker.image("${DOCKER_IMAGE}").push()
                    }
                }
            }
        }
        stage('Publish') {
            steps {
                script {
                    kubernetesDeploy(
                        configs: 'k8s/*.yaml',
                        kubeconfigId: "${KUBE_CREDENTIALS}"
                    )
                }
            }
        }
    }
}
