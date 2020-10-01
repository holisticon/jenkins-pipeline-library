#!/usr/bin/groovy
package de.holisticon.ci.jenkins

import static de.holisticon.ci.jenkins.utils.EscapeUtil.escapeGitBranchName

def isDevelopBranch() {
    env.BRANCH_NAME == 'develop'
}

def isProductionBranch() {
    env.BRANCH_NAME == 'master'
}

def isSupportBranch() {
    env.BRANCH_NAME.startsWith('support')
}

def isFeatureBranch() {
    env.BRANCH_NAME.startsWith('feature')
}

def getBranchNameEscaped() {
    return escapeGitBranchName(env.BRANCH_NAME)
}

return this;