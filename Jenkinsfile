pipeline {
  agent any

  parameters {
    booleanParam(name: 'HEADLESS', defaultValue: false, description: 'Run in headless mode')
  }

  tools {
    jdk 'JDK-17'
    maven 'Maven-3.9'
  }

  stages {

    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Test') {
      steps {
        bat "mvn clean test -Dheadless=%HEADLESS%"
      }
    }

    stage('Allure Report') {
      steps {
        bat "mvn allure:report"
        archiveArtifacts artifacts: 'target/allure-report/**,target/screenshots/**,target/logs/**,target/*.html', allowEmptyArchive: true
      }
    }
  }

  post {
    always {
      junit 'target/surefire-reports/*.xml'
    }
  }
}
