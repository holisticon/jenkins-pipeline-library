#!/usr/bin/groovy
package de.holisticon.ci.jenkins;

def setProjectVersion(project, version) {
    sh "/usr/libexec/PlistBuddy -c 'Set :CFBundleShortVersionString ${version}' '${project}'"
}

def setBuildnumber(project, buildNumber) {
    sh "/usr/libexec/PlistBuddy -c 'Set :CFBundleVersion ${buildNumber}' '${project}'"
}

return this;
