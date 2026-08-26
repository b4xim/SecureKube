pipeline{
    agent any

    stages {
        stage ('Build'){
            steps{
                echo 'Building..'
                run 'mvn clean package'
            }
        }
    }
}