#!/usr/bin/groovy
package de.holisticon.ci.jenkins;

import groovy.json.JsonSlurper

def getProjectVersion(){
    def file = readFile('pom.xml')
    def project = new XmlSlurper().parseText(file)
    return project.version.text()
}

return this;