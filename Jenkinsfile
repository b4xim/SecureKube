pipeline{
    agent any

    stages {
        stage ('Build'){
            steps{
                echo 'Building..'
                sh 'mvn clean package'
            }
        }
        stage('SonarQube Analysis') {
    def mvn = tool 'Default Maven';
    withSonarQubeEnv() {
      sh "${mvn}/bin/mvn clean verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=b4xim_SecureKube_e03b58c3-3a75-4c05-b229-633ee80e37b6 -Dsonar.projectName='SecureKube'"
    }
  }
    }
}