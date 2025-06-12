pipeline{
    agent any
    environment{
		BRANCH_NAME = "${env.BRANCH_NAME}"
		SPRING_PROFILE = "${BRANCH_NAME == 'dev' ? 'dev' : (BRANCH_NAME == 'release/sit' ? 'sit' : (BRANCH_NAME == 'release/uat' ? 'uat' : 'prod' ))}"
        DOCKER_IMAGE = 'shijipaul/library-management-system-app'
        DOCKER_CREDENTIALS_ID = 'docker-hub-creds'
		DOCKER_COMPOSE_FILE = "docker-compose-${SPRING_PROFILE}.yml"
    }
    stages{
        stage('Checkout Library App') {
            steps {
                    checkout scm
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
                       def app = docker.build("${DOCKER_IMAGE}:${SPRING_PROFILE}" ,'./library-app') 
                       app.push("${SPRING_PROFILE}")
                    }
                }
            }
        }
		stage('Cleanup Previous Deployment') {
            steps {
                echo 'Stopping any existing containers...'
                sh '''
                    docker-compose -f ${DOCKER_COMPOSE_FILE} down || true
                '''
            }
        }
        stage('deploy(Local Docker Compose)'){
            steps{
					echo "Deploying to environment: ${SPRING_PROFILE}"
                    sh '''
                       docker-compose -f ${DOCKER_COMPOSE_FILE} up -d --build
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
