pipeline {
    agent {
        docker { image 'node:lts-buster-slim' }
    }
    parameters{
      string(name: 'CODE_REPO', defaultValue: 'git@github.com:Jiraiya106/itransition_jenkins.git', description: 'Some description'),
      string(name: 'BRANCH', defaultValue: 'staging', description: 'Some description')
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
                //sh 'whoami'
                sh 'node --version'
                checkout([$class: 'GitSCM', branches: [[name: BRANCH]], doGenerateSubmoduleConfigurations: false,
                     extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'code/']], gitTool: 'Default', 
                     submoduleCfg: [],  userRemoteConfigs: [[credentialsId: '291fa4ed-3377-4281-b1db-92a0b7512fcd', url: CODE_REPO]]])
                //npm config set cache ${WORKSPACE}/cache --global
                sh 'ls ${WORKSPACE}/code'
                sh 'ls -li /usr/local/lib/'
                sh 'export HOME=${WORKSPACE} &&'+
                'mkdir -p npm-cache && npm config set prefix "${WORKSPACE}/npm-cache" &&'+
                'cd ${WORKSPACE}/code && npm install  && npm run build'
                
            }
              // script{
              //   echo "Message: ${params.message}, build number: ${env.BUILD_NUMBER}"
              //   //Иницилизируем рандом
              //   Random rnd = new Random()
              //   //Выставляем переменную которая веренется через 
              //   env.setProperty('returnData', rnd.nextInt())
              // }
            
    
    }
  }
}