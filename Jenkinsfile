pipeline{
    agent any
    environment{
        DOCKER_IMAGE = 'shijipaul/library-management-system-app'
        DOCKER_CREDENTIALS_ID = 'docker-hub-creds'
    }
    stages{
        stage('Checkout Library App') {
            steps {
                    git branch: 'feature/library',
                    url: 'https://github.com/shijipaul/library-management-system.git',
                    credentialsId: 'github-token'
                  }
            }

        stage('Build Library App') {
             steps {
                        sh '''
                            ls -la
                            cd library-app
                            mvn clean install -DskipTests
                        '''
                     }
            }
        stage('Docker Build & Push'){
            steps{
                script{
                    docker.withRegistry('https://index.docker.io/v1/',DOCKER_CREDENTIALS_ID){
                       def app = docker.build("${DOCKER_IMAGE}:latest" ,'./library-app') 
                       app.push("latest")
                    }
                }
            }
        }
        stage('deploy(Local Docker Compose)'){
            steps{
                    sh '''
                    docker-compose down || true
                    docker-compose up -d --build
                    '''
                }
                
            }
        }
        post{
                success{
                    echo 'Build and Deployment is successfull!'
                }
                failure{
                    echo 'Deployment is failed!'
                }
                
            }
    }