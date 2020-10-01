package de.holisticon.ci.jenkins


import com.lesfurets.jenkins.unit.BasePipelineTest
import org.junit.Before
import org.junit.Test

class GitTest extends BasePipelineTest {

    def git

    @Before
    void prepare() throws Exception {
        setUp()
        git = loadScript("./src/de/holisticon/ci/jenkins/Git.groovy")
    }

    @Test
    void should_detect_master_as_prod() throws Exception {
        binding.setVariable("env", [BRANCH_NAME: "master"])
        assert git.isProductionBranch()
        assert !git.isDevelopBranch()
        assert !git.isFeatureBranch()
        assert !git.isSupportBranch()
    }

    @Test
    void should_detect_developer_as_dev() throws Exception {
        binding.setVariable("env", [BRANCH_NAME: "develop"])
        assert git.isDevelopBranch()
        assert !git.isProductionBranch()
        assert !git.isFeatureBranch()
        assert !git.isSupportBranch()
    }

    @Test
    void should_detect_feature_branches() throws Exception {
        binding.setVariable("env", [BRANCH_NAME: "feature/123"])
        assert git.isFeatureBranch()
        assert !git.isDevelopBranch()
        assert !git.isProductionBranch()
        assert !git.isSupportBranch()
    }

    @Test
    void should_detect_support_branches() throws Exception {
        binding.setVariable("env", [BRANCH_NAME: "support/123"])
        assert git.isSupportBranch()
        assert !git.isDevelopBranch()
        assert !git.isProductionBranch()
        assert !git.isFeatureBranch()
    }

    @Test
    void should_detect_unknown_branches() throws Exception {
        binding.setVariable("env", [BRANCH_NAME: "pr/123"])
        assert !git.isSupportBranch()
        assert !git.isDevelopBranch()
        assert !git.isProductionBranch()
        assert !git.isFeatureBranch()
    }

    @Test
    void should_escape_branch_name() throws Exception {
        binding.setVariable("env", [BRANCH_NAME: "pr/123_abc"])
        assert git.getBranchNameEscaped() == "pr-123-abc"
    }
}