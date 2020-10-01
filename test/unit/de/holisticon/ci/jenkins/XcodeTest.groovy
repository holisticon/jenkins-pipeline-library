package de.holisticon.ci.jenkins

import com.lesfurets.jenkins.unit.BasePipelineTest
import org.junit.Before
import org.junit.Test

class XcodeTest extends BasePipelineTest {


    @Before
    void prepare() throws Exception {
        setUp()
        helper.registerAllowedMethod("sh", [Map.class, Closure.class], null)
    }

    @Test
    void should_work_with_checkYamlSyntax() throws Exception {
        def xcode = loadScript("./src/de/holisticon/ci/jenkins/Xcode.groovy")
        xcode.setProjectVersion('','')
        printCallStack()
        assertJobStatusSuccess()
    }

    @Test
    void should_work_with_setBuildnumber() throws Exception {
        def xcode = loadScript("./src/de/holisticon/ci/jenkins/Xcode.groovy")
        xcode.setBuildnumber('','')
        printCallStack()
        assertJobStatusSuccess()
    }

}