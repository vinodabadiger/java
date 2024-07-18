pipeline{
agent any

    stages{
        stage('checkout'){
            steps{
                git branch: 'main', credentialsId: 'git-cred', url: 'https://github.com/vinodabadiger/java.git'
            }
        }

        stage('build'){
            steps{
                sh "docker build -t java:1 . "
            }
        }
    }
}