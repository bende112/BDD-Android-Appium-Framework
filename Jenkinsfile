pipeline {
    agent any

    environment {
        ANDROID_HOME = '/opt/android-sdk'
        PATH = "${env.PATH}:${ANDROID_HOME}/tools:${ANDROID_HOME}/platform-tools"
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/bende112/BDD-Android-Appium-Framework.git'
            }
        }

        stage('Install Dependencies') {
            steps {
                sh './gradlew dependencies'
            }
        }

        stage('Run Tests') {
            steps {
                sh './gradlew connectedAndroidTest'
            }
        }
    }

    post {
        always {
            junit '**/build/test-results/**/*.xml'
        }
    }
}
