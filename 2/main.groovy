def runJob(jobName, params){
  def job
  try{
    job = build job: jobName, parameters: params
  }catch(err){
    return false
  }
  return job.getBuildVariables()
}

def firstJobResult
def secondJobResult

pipeline {
    agent any
    triggers { pollSCM('* * * * *') } 

      parameters {
        string(
			name: 'CODE_REPO',
			defaultValue: 'git@github.com:Jiraiya106/itransition_jenkins.git',
			description: 'REPO'
		)
		 string(
			name: 'BRANCH',
			defaultValue: 'develop',
			description: 'BRANCH'
		)
    booleanParam(
			name: 'BUILD',
			defaultValue: true,
			description: 'Build selected version'
		)
		booleanParam(
			name: 'QG',
			defaultValue: true,
			description: 'Run quality gates'
		)
		booleanParam(
			name: 'DOCKER',
			defaultValue: true,
			description: 'Build image'
		)

		booleanParam(
			name: 'DEPLOY',
			defaultValue: true,
			description: 'Deploy from docker'
		)

    }

    stages {
        stage('Init') { 
            steps { 
                echo 'Init'
                checkout scm
            }
        }
        stage('First child Job'){
            steps {
              script{
                firstJobResult = runJob('First', [
                  string(name: 'CODE_REPO', value: '${params.CODE_REPO}'),
                  string(name: 'BRANCH', value: '${params.BRANCH}')
                ])
                if(firstJobResult == false){
                  currentBuild.status = 'FAILURE'
                  return
                }
                echo "First job return data: ${firstJobResult.returnData}"
              }
            }
        }
        stage('Second child job') {
            steps {
              script{
                secondJobResult = runJob('Second', [
                  string(name: 'message', value: 'Hello second JOB from main pipeline'),
                  string(name: 'someData', value: firstJobResult.returnData)
                ])
                if(secondJobResult == false){
                  currentBuild.status = 'FAILURE'
                  return
                }
                echo "Second job return data: ${secondJobResult.returnData}"
              }
            }
        }
    }
}