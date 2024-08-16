pipeline {

    agent {
        label 'Slave_Induccion'
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: '3'))
        disableConcurrentBuilds()
    }

    tools {
        jdk 'JDK17'
        gradle 'Gradle8.8'
    }

    stages {
        stage('Checkout') {
            steps {
                echo "------------>Checkout<------------"
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/main']],
                    doGenerateSubmoduleConfigurations: false,
                    extensions: [],
                    gitTool: 'Default',
                    submoduleCfg: [],
                    userRemoteConfigs: [[
                        credentialsId: 'PLAYGROUND_AC',
                        url: 'https://git.ceiba.com.co/devops/quality-assurance/playground/base-unitarias-gradle.git'
                    ]]
                ])
            }
        }

        stage('Clean') {
            steps {
                echo "------------>Clean<------------"
                sh 'chmod +x ./gradlew'
                sh './gradlew --b ./build.gradle clean'
            }
        }

        stage('Unit Tests') {
            steps {
                echo "------------>Unit Tests<------------"
                sh 'chmod +x ./gradlew'
                sh './gradlew --b ./build.gradle clean'
                sh './gradlew --b ./build.gradle test'
                sh './gradlew --b ./build.gradle jacocoTestReport'
                sh './gradlew --b ./build.gradle pitest'
            }
        }

        stage('Publish JaCoCo Report') {
            steps {
                // Publish the JaCoCo report
                jacoco execPattern: 'build/jacoco/test.exec',
                       classPattern: 'build/classes/java/main',
                       sourcePattern: 'src/main/java',
                       exclusionPattern: ''
            }
        }

        stage('Publish PiTest Report') {
            steps {
                // Publish the PiTest report
                publishHTML(target: [
                    reportDir: 'build/reports/pitest',
                    reportFiles: 'index.html',
                    reportName: 'PiTest Mutation Report'
                ])
            }
        }

        stage('Static Code Analysis') {
            steps {
                echo '------------>Análisis de código estático<------------'
                withSonarQubeEnv('Sonar') {
                    sh "${tool name: 'SonarScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"
                }
            }
        }

        stage('Build') {
            steps {
                echo "------------>Build<------------"
                sh './gradlew --b ./build.gradle test'
            }
        }
    }

    post {
        always {
            echo 'This will always run'
        }

        success {
            echo 'This will run only if successful'
        }

        failure {
            echo 'This will run only if failed'
            mail(to: 'john.cortes@ceiba.com.co', subject: "Failed Pipeline: ${currentBuild.fullDisplayName}", body: "Something is wrong with ${env.BUILD_URL}")
        }
    }
}
