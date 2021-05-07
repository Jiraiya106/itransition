pipeline {
    agent {
        docker { image "ubuntu:latest" }
    }
    parameters{
      string(name: 'message', defaultValue: 'Default message', description: 'Some description')
      string(name: 'someData', defaultValue: '', description: 'Some input data')
    }
    stages {
        stage('Init') { 
            steps { 
                echo 'Init'
                checkout scm
            }
        }
        stage('Docker') { 
            steps { 
                sh 'cd ${WORKSPACE} && echo FROM nginx > ${WORKSPACE}/Dockerfile &&'+
                "echo COPY `find ${WORKSPACE}/release -type f` /var/www/html >> ${WORKSPACE}/Dockerfile"
              // script{
              //   sh 'env'
              //   echo "Message: ${params.message}, Some data: ${params.someData}, build number: #${env.BUILD_NUMBER}"
              //   env.setProperty('returnData', "Some data from second job, ${env.BUILD_URL}" )
              // }
            }
        }
    }
}