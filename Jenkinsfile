def img
pipeline{
agent any
environment{
    registry="vinoda32/java"
}

    stages{
        stage('checkout'){
            steps{
                git branch: 'main', credentialsId: 'git-cred', url: 'https://github.com/vinodabadiger/java.git'
            }
        }

        stage('build'){
            steps{
                script{
                    img = registry + ":${env.BUILD_ID}"
                    print "${img}"
                    sh "docker build -t $img ."
                }
                
            }
        }

        stage('push'){
            steps{
                withDockerRegistry(credentialsId: 'docker-cred', url: 'https://index.docker.io/v1/') {
                sh "docker push $img"
                }
            }
        }

        stage('ssh'){
            steps{
                script {
                    sshPublisher(
                        publishers: [
                            sshPublisherDesc(
                                configName: 'production', 
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

                                        // sshTransfer(
                                        //     execCommand: 'docker stop java ', 
                                        //     execTimeout: 120000, 
                                        // )
                                    ], 
                                        usePromotionTimestamp: false, 
                                        useWorkspaceInPromotion: false, 
                            
                            )
                        ]
                    )
                }
            }
        
        }

            stage('cleanup'){
                steps{
                    sh "docker rmi $img "
                }
            }
    }
}