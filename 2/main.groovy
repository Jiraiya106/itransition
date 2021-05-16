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
                  string(name: 'message', value: 'Hello first JOB from main pipeline')
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