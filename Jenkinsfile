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
            steps {
                echo 'SonarQube Analysis..'
                withSonarQubeEnv('SecureKube') {
                sh "mvn clean verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=b4xim_SecureKube_e03b58c3-3a75-4c05-b229-633ee80e37b6 -Dsonar.projectName='SecureKube'"
                }
            }
        }
        stage('Quality Gate') {
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                echo 'Building Docker Image..'
                sh 'docker build -t securekube:latest .'
            }
        }

    }
}

