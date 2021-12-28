node {
    def mvnHome
    stage('clone the code') { 
        git 'https://github.com/harichinn/mvnproj.git'
        mvnHome = tool 'maven'
    }
    stage('Build the code') {
        withEnv(["MVN_HOME=$mvnHome"]) {
            if (isUnix()) {
                sh '"$MVN_HOME/bin/mvn" -Dmaven.test.failure.ignore clean package install'
            } else {
                bat(/"%MVN_HOME%\bin\mvn" -Dmaven.test.failure.ignore clean package/)
            }
        }
    }
    stage('deploy the code') {
        sh '''
        ls -l
        sudo cp /var/lib/jenkins/.m2/repository/DEVOPS/aws1/1.06/aws1-1.06.war /var/lib/tomcat8/webapps/
        '''
    }
}
