package com.cassius.spring.assembly.test.junits;

import com.cassius.spring.assembly.test.common.setting.processor.SpringBean;
import com.cassius.spring.assembly.test.usages.domain.Service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.internal.util.MockUtil;
import org.springframework.test.context.ContextConfiguration;

/**
 * OldJUnit4Test
 *
 * @author Cassius Cai
 * @version v 0.1 8/5/15 23:53 Exp $
 */
@ContextConfiguration(locations = { "classpath:META-INF/spring/spring.xml", })
public class NewJUnit4Test extends AbstractJUnit4SpringAssemblyTests {

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
        Assertions.assertThat(new MockUtil().isMock(service)).isFalse();
    }
}
