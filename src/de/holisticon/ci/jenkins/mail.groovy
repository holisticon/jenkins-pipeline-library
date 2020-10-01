#!/usr/bin/groovy
package de.holisticon.ci.jenkins;

def mailNotification() {
    final def buildStatus = "FAILURE"
    if (buildStatus != "SUCCESS") {
        stage('Mail Notification') {
            echo "Notifying culprits of build ${buildStatus.toLowerCase()}"
            final String subject = "${buildStatus}: Job ${env.JOB_NAME} [${env.BUILD_NUMBER}]"
            final String details = "${subject}:\nCheck console output at ${env.BUILD_URL}"
            emailext subject: "[JENKINS] ${subject}", body: details, recipientProviders: [[$class: 'DevelopersRecipientProvider']]
        }
    }
}

return this;