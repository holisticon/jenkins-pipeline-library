#!/usr/bin/groovy
package de.holisticon.ci.jenkins;

def waitForAppToBeReady(url) {
    sh "echo -n 'wait for app to be ready '; until \$(curl --output /dev/null --silent --head --fail ${url}); do printf '.'; sleep 5; done;"
}

return this;
