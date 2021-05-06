pipeline {
    agent {
        docker { image 'ubuntu:latest' }
    }
    parameters{
      string(name: 'message', defaultValue: 'Default message', description: 'Some description')
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
                echo "Message: ${params.message}, build number: ${env.BUILD_NUMBER}"
                //Иницилизируем рандом
                Random rnd = new Random()
                //Выставляем переменную которая веренется через 
                env.setProperty('returnData', rnd.nextInt())
              }
            }
        }
    }
}