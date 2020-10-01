#!/usr/bin/groovy
package de.holisticon.ci.jenkins;

def checkYamlSyntax(dir) {
    sh """#!/bin/bash -e
        set -eu
        if [ -f ${dir}/requirements.yml ]; then ansible-galaxy install -r ${dir}/requirements.yml -f; fi
        for file in \$(find ${dir} -maxdepth 1 -name "*yml"  \\! -name "requirements.yml" \\! -name "vars.yml" \\! -name "secrets.yml" );
          do ansible-playbook --syntax-check \$file
        done

    """
}


def runEnd2EndTestInDocker(List<String> commands) {
    sh "docker pull ju2wheels/ansible:2.x-debian-9"
    withDockerContainer(args: '--cap-add=NET_ADMIN --user root --tty --entrypoint=""  -v /var/run/docker.sock:/var/run/docker.sock', image: 'ju2wheels/ansible:2.x-debian-9') {

        // holi base setup
        sh "useradd -m backuppc"
        sh "apt-get -y update"
        sh "apt-get -y install unattended-upgrades apt-listchanges wget sudo nginx docker ufw"

        // test project ansible
        sh "ansible-galaxy --version"
        for (command in commands) {
            sh "${command}"
        }
    }
}

return this;
