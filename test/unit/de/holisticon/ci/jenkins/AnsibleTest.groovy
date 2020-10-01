package de.holisticon.ci.jenkins

import com.lesfurets.jenkins.unit.BasePipelineTest
import org.junit.Before
import org.junit.Test

class AnsibleTest extends BasePipelineTest {

    @Before
    void prepare() throws Exception {
        setUp()
        helper.registerAllowedMethod("sh", [Map.class, Closure.class], null)
        helper.registerAllowedMethod("withDockerContainer", [Map.class, Closure.class], null)
    }

    @Test
    void should_work_with_checkYamlSyntax() throws Exception {
        def ansible = loadScript("./src/de/holisticon/ci/jenkins/Ansible.groovy")
        ansible.checkYamlSyntax('')
        printCallStack()
        assertJobStatusSuccess()
    }

    @Test
    void should_work_with_runEnd2EndTestInDocker() throws Exception {
        def ansible = loadScript("./src/de/holisticon/ci/jenkins/Ansible.groovy")
        ansible.runEnd2EndTestInDocker([
                'ansible-galaxy install -r ansible/requirements.yml',
                'ansible-playbook -i "localhost," -c local ansible/jenkins.yml --tags="app,docker,node" --extra-vars "skip_nginx=true ansible_user=root pyenv_owner=jenkins"  --extra-vars "@ansible/vars.yml"'
        ])
        printCallStack()
        assertJobStatusSuccess()
    }
}