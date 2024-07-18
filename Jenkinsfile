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


          stage('tag'){
            steps{
                sh "docker tag java:1 vinoda32/java:1"
            }
        }

        stage('push'){
            steps{
                withDockerRegistry(credentialsId: 'docker-cred', url: 'https://index.docker.io/v1/') {
                sh "docker push vinoda32/java:1"
                }
            }
        }


        sshPublisher(
            publishers: [
                sshPublisherDesc(
                    configName: 'production', 
                     verbose: true ,
                    transfers: [
                        sshTransfer(
                            execCommand: 'ls', 
                            execTimeout: 120000, 
                        ),

                         sshTransfer(
                            execCommand: 'pwd', 
                            execTimeout: 120000, 
                        )
                    ], 
                            usePromotionTimestamp: false, 
                            useWorkspaceInPromotion: false, 
                            
                )
            ]
        )
    }
}