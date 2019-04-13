pipeline {
  agent any
  options { skipDefaultCheckout() }
  stages {
    stage('SCM') {
      steps {
        checkout scm
      }
    }
    stage('Compile') {
      agent {
        docker {
          image 'maven:3.6.0-jdk-8-alpine'
          args '-v /root/.m2/repository:/root/.m2/repository'
          // to use the same node and workdir defined on top-level pipeline for all docker agents
          reuseNode true
        }

      }
      steps {
        sh ' mvn clean compile'
      }
    }
   stage('Test') {
      agent {
        docker {
          image 'maven:3.6.0-jdk-8-alpine'
          args '-v /root/.m2/repository:/root/.m2/repository'
          reuseNode true
        }
      }
      steps {
        sh 'mvn test'
        //  stash(name: 'testedproject', useDefaultExcludes: false)
      }
      post {
        always {
          junit 'target/surefire-reports/**/*.xml'
        }
      }
    }
  }
}