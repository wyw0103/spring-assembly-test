package com.cassius.spring.assembly.test.junits;

import com.cassius.spring.assembly.test.common.setting.configure.SpringAssemblyConfigure;
import com.cassius.spring.assembly.test.common.setting.configure.SpringContextConfigure;
import com.cassius.spring.assembly.test.common.setting.processor.SpringBean;
import com.cassius.spring.assembly.test.usages.domain.Service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.internal.util.MockUtil;

/**
 * OldJUnit4Test
 *
 * @author Cassius Cai
 * @version v 0.1 8/5/15 23:53 Exp $
 */
@SpringContextConfigure({ "META-INF/spring/spring.xml" })
@SpringAssemblyConfigure(createSpy = true)
public class OldJUnit4WithSpyTest extends JUnit4SpringAssemblyTest {

    /**
     * The Service.
     */
    @SpringBean
    private Service service;

    /**
     * Test void.
     */
    @Test
    public void test() {
        Assertions.assertThat(service).isNotNull();
        Assertions.assertThat(new MockUtil().isMock(service)).isTrue();
    }
}
