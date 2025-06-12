pipeline{
    agent any
    environment{
		BRANCH_NAME = "${env.BRANCH_NAME}"
        DOCKER_IMAGE = 'shijipaul/library-management-system-app'
        DOCKER_CREDENTIALS_ID = 'docker-hub-creds'
		SPRING_PROFILE = getProfile(BRANCH_NAME)
		DOCKER_COMPOSE_FILE = "docker-compose-${SPRING_PROFILE}.yml"
    }
    stages{
		stage('Set Environment') {
            steps {
                script {
                
                    echo "Branch: ${BRANCH_NAME}"
                    echo "Spring profile: ${SPRING_PROFILE}"
                    echo "Docker Compose file: ${DOCKER_COMPOSE_FILE}"
                }
            }
        }

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
def getProfile(String branch) {
			if (branch == 'dev') return 'dev'
			if (branch == 'release/sit') return 'sit'
			if (branch == 'release/uat') return 'uat'
			if (branch == 'release/prod') return 'prod'
			return 'dev' // fallback
}