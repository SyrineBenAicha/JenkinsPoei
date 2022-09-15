pipeline {
    agent any

    stages {
        stage('Init') {
            steps {
                echo "Testing..."
            }
        }
        stage('Run') {
            steps {
                 bat "mvn test"
            }
        }
    }

    post {
            always {
              step([$class: 'Publisher', reportFilenamePattern: '**/testng-results.xml'])
            }
          }
}