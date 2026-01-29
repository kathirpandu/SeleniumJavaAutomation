pipeline {
  agent any
  parameters {
    booleanParam(name: 'HEADLESS', defaultValue: false, description: 'Headless run')
  }
  tools {
    jdk 'JDK-17'
    maven 'Maven-3.9'
  }
  stages {
    stage('Checkout') { steps { checkout scm } }
    stage('Test') { steps { sh "mvn clean test -Dheadless=${params.HEADLESS}" } }
    stage('Allure Report') {
      steps {
        sh "mvn allure:report"
        archiveArtifacts artifacts: 'target/allure-report/**,target/screenshots/**,target/logs/**,target/*.html', allowEmptyArchive: true
      }
    }
  }
  post { always { junit 'target/surefire-reports/*.xml' } }
}
