pipeline {
    agent {
        docker { image 'node:lts-buster-slim' }
    }
    // parameters{
    //   string(name: 'message', defaultValue: 'Default message', description: 'Some description')
    //   string(name: 'someData', defaultValue: '', description: 'Some input data')
    // }
    stages {
        stage('Init') { 
            steps { 
                echo 'Init'
                checkout scm
            }
        }
        stage('Docker') { 
            steps { 
                sh 'cd /var/lib/jenkins/workspace && echo FROM nginx > /var/lib/jenkins/workspace/Dockerfile &&'+
                "echo COPY `find /var/lib/jenkins/workspace/First/code -type f` /var/www/html >> /var/lib/jenkins/workspace/Dockerfile"
              script{
                def app = docker.build("test")
              //   sh 'env'
              //   echo "Message: ${params.message}, Some data: ${params.someData}, build number: #${env.BUILD_NUMBER}"
              //   env.setProperty('returnData', "Some data from second job, ${env.BUILD_URL}" )
              }
            }
        }
    }
}