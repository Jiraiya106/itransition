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
        stage('Processing') { 
            steps { 
              script{
                sh 'env'
                echo "Message: ${params.message}, Some data: ${params.someData}, build number: #${env.BUILD_NUMBER}"
                env.setProperty('returnData', "Some data from second job, ${env.BUILD_URL}" )
              }
            }
        }
    }
}