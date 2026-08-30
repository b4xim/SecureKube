pipeline{
    agent any

    stages {
        stage ('Build'){
            steps{
                echo 'Building..'
                sh 'mvn clean package'
            }
        }
        stage ('Sonar Qube Analysis'){
            steps{
                withSonarQubeEnv('SonarQube'){
                    sh 'mvn sonar:sonar'
                }
                
            }
        }
    }
}