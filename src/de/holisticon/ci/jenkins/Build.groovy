#!/usr/bin/groovy
package de.holisticon.ci.jenkins;

def releaseNotes(b) {
    b.changeSets.collect { cs ->
        cs.collect { entry ->
            / - ${entry.msg} (${entry.author.fullName})/
        }.join('\n')
    }.join('')
}

def summarizeBuild(b) {
    b.changeSets.collect { cs ->
        /kind=${cs.kind}; entries=/ + cs.collect { entry ->
            /${entry.commitId} by ${entry.author.id} ~ ${entry.author.fullName} on ${new Date(entry.timestamp)}: ${entry.msg}:  /+ entry.affectedFiles.collect { file ->
                /${file.editType.name} ${file.path}/
            }.join('; ')
        }.join(', ')
    }.join(' & ')
}

return this;