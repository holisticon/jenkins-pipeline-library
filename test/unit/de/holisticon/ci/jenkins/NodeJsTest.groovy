package de.holisticon.ci.jenkins

import com.lesfurets.jenkins.unit.BasePipelineTest
import org.junit.Before
import org.junit.Test

class NodeJsTest extends BasePipelineTest {


    @Before
    void prepare() throws Exception {
        setUp()
        helper.registerAllowedMethod("sh", [Map.class, Closure.class], null)
    }

    @Test
    void should_work_with_nvm_without_opts() throws Exception {
        def nodeJS = loadScript("./src/de/holisticon/ci/jenkins/NodeJS.groovy")
        nodeJS.nvm('install')
        printCallStack()
        assertJobStatusSuccess()
    }

    @Test
    void should_work_with_nvm_with_opts() throws Exception {
        def nodeJS = loadScript("./src/de/holisticon/ci/jenkins/NodeJS.groovy")
        nodeJS.nvm('install', '')
        printCallStack()
        assertJobStatusSuccess()
    }

    @Test
    void should_work_with_nvmRun_without_opts() throws Exception {
        def nodeJS = loadScript("./src/de/holisticon/ci/jenkins/NodeJS.groovy")
        nodeJS.nvmRun('install')
        printCallStack()
        assertJobStatusSuccess()
    }

    @Test
    void should_work_with_nvmRun_with_opts() throws Exception {
        def nodeJS = loadScript("./src/de/holisticon/ci/jenkins/NodeJS.groovy")
        nodeJS.nvmRun('install', '')
        printCallStack()
        assertJobStatusSuccess()
    }

}