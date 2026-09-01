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
                sh "docker build -t securekube:${BUILD_NUMBER} ."
            }
        }
        stage('Scan Docker Image') {
            steps {
                echo 'Scanning Docker Image..'
                sh "docker run --rm -v /var/run/docker.sock:/var/run/docker.sock aquasec/trivy image securekube:${BUILD_NUMBER}"
            }
            
        }
        stage('Push to Nexus') {
    steps {
        echo 'Pushing Docker Image to Nexus..'

        withCredentials([usernamePassword(
            credentialsId: 'nexus-credentials',
            usernameVariable: 'NEXUS_USERNAME',
            passwordVariable: 'NEXUS_PASSWORD'
        )]) {
            sh '''
                echo "$NEXUS_PASSWORD" | docker login 172.31.38.62:8082 \
                    -u "$NEXUS_USERNAME" --password-stdin

                docker tag securekube:${BUILD_NUMBER} 172.31.38.62:8082/securekube:${BUILD_NUMBER}

                docker push 172.31.38.62:8082/securekube:${BUILD_NUMBER}
            '''
        }
    }
}
    }
} 
