#!/usr/bin/groovy
package de.holisticon.ci.jenkins

def decrypt(gitSecretPassphrase) {
    sh "SECRETS_GPG_COMMAND=\"gpg --batch --yes --passphrase ${gitSecretPassphrase}\" git-secret reveal -f"
}

return this;
