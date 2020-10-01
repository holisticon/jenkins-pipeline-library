# Holistic Jenkins Pipeline Library

- [Holistic Jenkins Pipeline Library](#holistic-jenkins-pipeline-library)
  - [Why](#why)
  - [Repository Structure](#repository-structure)
  - [Usage](#usage)
  - [Additional Links](#additional-links)

## Why

An Shared Library is defined with a name, a source code retrieval method such as by SCM, and optionally a default version. The name should be a short identifier as it will be used in scripts.

The version could be anything understood by that SCM; for example, branches, tags, and commit hashes all work for Git. You may also declare whether scripts need to explicitly request that library (detailed below), or if it is present by default. Furthermore, if you specify a version in Jenkins configuration, you can block scripts from selecting a different version.

This repository contains examples of a Jenkins Shared Library and JenkinsPipelineUnit tests.

## Repository Structure
```
.
├── src
│   └── org
│       └── hcm
│           └── libjenkins
│               └── *.groovy         # Examples of Library class
├── test
│   ├── integration                  # Integration tests
|   |   |── jobs                     # Template pipeline scripts to load the lib
│   |        │                       # are used by integration tests
|   |
│   └── unit
|       |── jobs                     # Template pipeline scripts to load the lib
│       |    │                       # are used by unit tests
│       |    └── template.groovy
│       └── *.groovy                 # JenkinsPipelineUnit tests
└── vars
    └── *.groovy                     # Jenkins pipeline shared library vars objects
```

## Usage

see [here](USAGE.md).

## Additional Links
* https://jenkins.io/doc/book/pipeline/shared-libraries/
* https://www.cloudbees.com/blog/top-10-best-practices-jenkins-pipeline-plugin
* https://github.com/lesfurets/JenkinsPipelineUnit
* https://github.com/mkobit/jenkins-pipeline-shared-libraries-gradle-plugin
* https://github.com/jenkinsci/jenkins-test-harness