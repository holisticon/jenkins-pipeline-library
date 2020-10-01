package de.holisticon.ci.jenkins.utils

class EscapeUtil implements Serializable {

    private static final long serialVersionUID = 1L


    def static escapeGitBranchName(branchName) {
        try {
            return branchName.replaceAll('/', '-').replaceAll('_', '-').replaceAll('@', '')
        } catch (ignored) {
            return "master"
        }

    }

}
