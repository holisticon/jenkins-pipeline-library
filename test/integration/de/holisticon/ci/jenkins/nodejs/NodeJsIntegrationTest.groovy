package de.holisticon.ci.jenkins.nodejs

import com.lesfurets.jenkins.unit.BasePipelineTest
import org.junit.Before
import org.junit.Test

class NodeJsIntegrationTest extends BasePipelineTest {

    @Before
    void prepare() throws Exception {
        setUp()
    }

    @Test
    void should_work_with_npm_install() throws Exception {
        def nodeJS = loadScript("./jobs/Jenkinsfile")
        nodeJS.nvm('install')
        printCallStack()
        assertJobStatusSuccess()
    }

    @Test
    void should_handle_npm_run_target() throws Exception {
        def nodeJS = loadScript("./jobs/Jenkinsfile")
        nodeJS.nvmRun('test2')
        printCallStack()
        assertJobStatusSuccess()
    }
}