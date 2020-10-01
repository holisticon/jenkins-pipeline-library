#!/usr/bin/groovy
package de.holisticon.ci.jenkins;

def getProjectVersion(){
    def file = readFile('pom.xml')
    def project = new XmlSlurper().parseText(file)
    return project.version.text()
}

return this;