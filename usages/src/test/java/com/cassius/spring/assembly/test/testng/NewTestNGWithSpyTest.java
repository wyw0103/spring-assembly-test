package com.cassius.spring.assembly.test.testng;

import com.cassius.spring.assembly.test.common.setting.configure.SpringContextConfigure;
import com.cassius.spring.assembly.test.common.setting.processor.SpringBean;
import com.cassius.spring.assembly.test.usages.domain.Service;

import org.assertj.core.api.Assertions;
import org.mockito.internal.util.MockUtil;
import org.testng.annotations.Test;

/**
 * OldJUnit4Test
 *
 * @author Cassius Cai
 * @version v 0.1 8/5/15 23:53 Exp $
 */
@SpringContextConfigure({ "META-INF/spring/spring.xml",
                          "classpath:META-INF/spring/spring-assembly-test-common.xml" })
public class NewTestNGWithSpyTest extends TestNGSpringAssemblyTest {

    @SpringBean
    private Service service;

    @Test
    public void test() {
        Assertions.assertThat(service).isNotNull();
        Assertions.assertThat(new MockUtil().isMock(service)).isTrue();
    }
}