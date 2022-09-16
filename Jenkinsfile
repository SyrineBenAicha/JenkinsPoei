pipeline {
    agent any

    triggers{cron{'0 12 * * 1-5}}'}} // lancer periodiquement

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
        post {
                always {
                  step([$class: 'Publisher', reportFilenamePattern: '**/testng-results.xml'])
                }
              }
    }


          }
}