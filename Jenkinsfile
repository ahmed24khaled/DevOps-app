pipeline {
  agent any
  stages {
    stage('SCM') {
      steps {
        git(url: 'https://github.com/ahmed24khaled/DevOps-app.git', branch: 'develop')
      }
    }
    stage('Compile') {
      agent {
        docker {
          image 'maven:3.6.0-jdk-8-alpine'
        }

      }
      steps {
        sh ' mvn compile'
      }
    }
  }
}