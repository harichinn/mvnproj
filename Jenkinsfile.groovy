pipeline {
    agent any

    tools {
        maven "maven"
    }
    stages {
        stage('Build the code') {
            steps {
                git 'https://github.com/harichinn/mvnproj.git'
                echo "building the code"
                sh "mvn -Dmaven.test.failure.ignore=true clean package install"
            }
        }
        stage('deploy the code') {
            steps {
                sh '''
                pwd
                sudo cp /var/lib/jenkins/.m2/repository/DEVOPS/aws1/1.06/aws1-1.06.war /var/lib/tomcat8/webapps/
                ls -l
                '''
            }
        }
    }
}
