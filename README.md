# Holistic Jenkins Pipeline Library

## Why

An Shared Library is defined with a name, a source code retrieval method such as by SCM, and optionally a default version. The name should be a short identifier as it will be used in scripts.

The version could be anything understood by that SCM; for example, branches, tags, and commit hashes all work for Git. You may also declare whether scripts need to explicitly request that library (detailed below), or if it is present by default. Furthermore, if you specify a version in Jenkins configuration, you can block scripts from selecting a different version.


## Usage

### <a name="nodejs"></a> NodeJS

To ease the support of multiple node versions
```
#!/usr/bin/groovy
@Library('holisticon-build-library')
def nodeJS = new de.holisticon.ci.jenkins.NodeJS()


node {
  // npm install:
  nodeJS.nvm('install')
  // npm run build  
  nodeJS.nvmRun('build')
  // prop=abc npm run build
  nodeJS.nvmRun('build','prop=abc')
  // npm version -m "..."
  nodeJS.nvm("version -m\"$COMMIT_MESSAGE\"")
  // npm publish
  nodeJS.nvm("publish")
}

```

### <a name="maven"></a> Maven
```
#!/usr/bin/groovy
@Library('holisticon-build-library')
def maven = new de.holisticon.ci.jenkins.Maven()


node {
  echo maven.getProjectVersion()

}

```

### <a name="git"></a> Git
```
#!/usr/bin/groovy
@Library('holisticon-build-library')
def git = new de.holisticon.ci.jenkins.Git()


node {
  if (git.isDevelopBranch()){
  
  }

}

```

### <a name="utils"></a> Utils
```
#!/usr/bin/groovy
@Library('holisticon-build-library')
def utils = new de.holisticon.ci.jenkins.Utils()


node {
  utils.waitForAppToBeReady('localhost:8080')
}

```
