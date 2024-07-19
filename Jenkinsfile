pipeline {
    agent any

    stages{
        stage('checkout'){
            steps{
                git branch: 'main', credentialsId: 'git-cred', url: 'https://github.com/vinodabadiger/java.git'
            }
        }

        stage('build'){
            steps{
               sh "docker build -t java:1 ."
            }
        }

         stage('tag'){
            steps{
               sh "docker tag java:1 vinoda32/java:2"
            }
        }

        stage('push'){
            steps{
                withDockerRegistry(credentialsId: 'docker-cred', url: 'https://index.docker.io/v1/') {
                    sh "docker push vinoda32/java:2"
                }
            }
        }
    }
}