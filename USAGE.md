# Holistic Jenkins Pipeline Library

- [Holistic Jenkins Pipeline Library](#holistic-jenkins-pipeline-library)
  - [Usage](#usage)
    - [Ansible](#ansible)
    - [Build](#build)
    - [NodeJS](#nodejs)
    - [Maven](#maven)
    - [<a name="git"></a> Git](#-git)
    - [GitSecret](#gitsecret)
    - [Utils](#utils)
    - [Xcode](#xcode)
## Usage

### Ansible

```
#!/usr/bin/groovy
@Library('holisticon-build-library') _


node {
  // verify YAML syntax of scripts in folder ansible
  ansible.checkYamlSyntax('ansible')
  // run end2end tests
  ansible.runEnd2EndTestInDocker([
  'ansible-galaxy install -r ansible/requirements.yml',
  'ansible-playbook -i "localhost," -c local ansible/jenkins.yml --tags="app,docker,node" --extra-vars "skip_nginx=true ansible_user=root pyenv_owner=jenkins"  --extra-vars "@ansible/vars.yml"'
  ])
}

```

### Build

```
#!/usr/bin/groovy
@Library('holisticon-build-library') _


node {
  // get changes for current build
  def releaseNotes = build.releaseNotes(currentBuild)
  // get summary for current build
  def buildSummary = build.summarizeBuild(currentBuild)
}

```

### NodeJS

To ease the support of multiple node versions
```
#!/usr/bin/groovy
@Library('holisticon-build-library') _

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
  nodeJS.publish('.')
  // npm publish snapshot with build number and branchname for next label in current directory
  nodeJS.publishSnapshot('.', env.BUILD_NUMBER, env.BRANCH_NAME)
  // get version from package.json
  echo nodeJS.getVersionFromPackageJSON()
}

```

### Maven
```
#!/usr/bin/groovy
@Library('holisticon-build-library') _

node {
  echo maven.getProjectVersion()

}

```

### <a name="git"></a> Git
```
#!/usr/bin/groovy
@Library('holisticon-build-library') _

node {
  if (git.isDevelopBranch()){

  }

}

```


### GitSecret
```
#!/usr/bin/groovy
@Library('holisticon-build-library') _

node {
  gitSecret.decrypt('passphrase')

}

```

### Utils

Wait for server url to be available
```
#!/usr/bin/groovy
@Library('holisticon-build-library') _

node {
  utils.waitForAppToBeReady('localhost')

}

```

### Xcode

Set bundle version
```
#!/usr/bin/groovy
@Library('holisticon-build-library') _

node {
    def buildNumber = env.BUILD_NUMBER
    xcode.setBuildnumber('confiis/Info.plist', buildNumber)

}

```

Set app version
```
#!/usr/bin/groovy
@Library('holisticon-build-library') _

node {
    xcode.setProjectVersion('confiis/Info.plist', '1.2.0')

}

```
