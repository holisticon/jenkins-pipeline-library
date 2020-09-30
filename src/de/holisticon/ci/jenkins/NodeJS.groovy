#!/usr/bin/groovy
package de.holisticon.ci.jenkins

def nvm(runTarget, opts = null) {
    def prefix = ""
    if (opts != null) {
        prefix = opts + " "
    }
    sh """#!/bin/bash -e
        NVM_DIR=
        source ~/.nvm/nvm.sh
        nvm use
        ${prefix}npm ${runTarget}
    """
}

def nvmRun(runTarget, opts = null) {
    def prefix = ""
    if (opts != null) {
        prefix = opts + " "
    }
    sh """#!/bin/bash -e
        NVM_DIR=
        source ~/.nvm/nvm.sh
        nvm use
        ${prefix}npm run ${runTarget}
    """
}

def nvmNode(command, opts = null) {
    def prefix = ""
    if (opts != null) {
        prefix = opts + " "
    }
    sh """#!/bin/bash -e
        NVM_DIR=
        source ~/.nvm/nvm.sh
        nvm use
        ${prefix}node ${command}
    """
}

def readJson(text) {
    def response = new groovy.json.JsonSlurperClassic().parseText(text)
    jsonSlurper = null
    echo "response:$response"
    return response
}

def publishSnapshot(directory, buildNumber, name) {
    dir(directory) {
        // get current package version
        def packageJsonText = readFile encoding: 'UTF-8', file: 'package.json'
        def packageJson = readJson(packageJsonText)
        def currentVersion = packageJson.version
        // add build number for maven-like snapshot
        def prefix = name.replaceAll('/','-').replaceAll('_','-')
        def newVersion = "${currentVersion}-${prefix}-${buildNumber}"
        // publish snapshot to NPM
        sh "npm version ${newVersion} --no-git-tag-version && npm publish --tag next"
    }
}

return this;