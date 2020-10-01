package de.holisticon.ci.jenkins

import com.lesfurets.jenkins.unit.BasePipelineTest
import org.junit.*

class TestExampleJob extends BasePipelineTest {

    @Before
    void prepare() throws Exception {
        setUp()
    }

    @Test
    void should_execute_without_errors() throws Exception {
        def script = loadScript("./test/unit/de/holisticon/ci/jenkins/jobs/exampleJob.jenkins")
        //script.execute()
      //  printCallStack()
    }
}