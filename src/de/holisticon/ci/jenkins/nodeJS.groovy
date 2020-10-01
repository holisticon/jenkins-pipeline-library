#!/usr/bin/groovy
package de.holisticon.ci.jenkins;

def initNvmShellCommand(prefix, command, suffix) {
    sh """#!/bin/bash
        NVM_DIR=
        source ~/.nvm/nvm.sh
        nvm install
        ${prefix} ${command} ${suffix}"""
}

def nvmWithOpts(opts, command, suffix) {
    def prefix = ""
    if (opts != null) {
        prefix = opts + " "
    }
    initNvmShellCommand(prefix, command, suffix)
}

def nvm(target, opts = null) {
    nvmWithOpts(opts, "npm", target)
}

def nvmRun(runTarget, opts = null) {
    nvmWithOpts(opts, "npm run", runTarget)
}

def nvmNode(nodeCommand, opts = null) {
    nvmWithOpts(opts, "node", nodeCommand)
}

def readJson(text) {
    def response = new groovy.json.JsonSlurperClassic().parseText(text)
    jsonSlurper = null
    echo "response:$response"
    return response
}

def getVersionFromPackageJSON() {
    dir(".") {
        def packageJson = readJSON file: 'package.json'
        return packageJson.version
    }
}

def publishSnapshot(directory, buildNumber, name) {
    dir(directory) {
        // get current package version
        def currentVersion = getVersionFromPackageJSON()
        // add build number for maven-like snapshot
        def prefix = name.replaceAll('/', '-').replaceAll('_', '-').replaceAll('@', '')
        def newVersion = "${currentVersion}-${prefix}-${buildNumber}"
        // publish snapshot to NPM
        nvmWithOpts(null, "npm", "version ${newVersion} --no-git-tag-version && npm publish --tag next")
    }
}

def publish(directory) {
    dir(directory) {
        // publish release to NPM
        nvmWithOpts(null, "npm", "publish")
    }
}

return this;