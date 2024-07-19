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

        stage('ssh'){
            steps{
                script{
                    sshPublisher(
                        publishers: [
                            sshPublisherDesc(
                                configName: 'prod',
                                verbose: true , 
                                    transfers: [
                                        sshTransfer( 
                                            execCommand: 'docker images', 
                                            execTimeout: 120000, 
                                        ),

                                        sshTransfer( 
                                            execCommand: 'docker ps', 
                                            execTimeout: 120000, 
                                        ),

                                         sshTransfer( 
                                            execCommand: 'docker pull vinoda32/java:2', 
                                            execTimeout: 120000, 
                                        ),

                                          sshTransfer( 
                                            execCommand: 'docker run -d -p 8080:8080 vinoda32/java:2', 
                                            execTimeout: 120000, 
                                        ),
                                    ], 
                                            usePromotionTimestamp: false, 
                                            useWorkspaceInPromotion: false, 
                                            
                            )
                        ]
                    )
                }
            }
        }
       
    }
}