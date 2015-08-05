/**********************************************************************************************************************
 * Copyright 2011-2015 Cassius Cai <cassiuscai@gmail.com>                                                             *
 *                                                                                                                    *
 * Licensed under the Apache License, Version 2.0 (the "License");                                                    *
 * you may not use this file except in compliance with the License.                                                   *
 * You may obtain a copy of the License at                                                                            *
 *                                                                                                                    *
 *     http://www.apache.org/licenses/LICENSE-2.0                                                                     *
 *                                                                                                                    *
 * Unless required by applicable law or agreed to in writing, software                                                *
 * distributed under the License is distributed on an "AS IS" BASIS,                                                  *
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.                                           *
 * See the License for the specific language governing permissions and                                                *
 * limitations under the License.                                                                                     *
 **********************************************************************************************************************/
package com.cassius.spring.assembly.test.junits;

import com.cassius.spring.assembly.test.common.core.SpringAssemblyTestUtil;
import com.cassius.spring.assembly.test.common.process.InjectIntoProcessor;
import com.cassius.spring.assembly.test.common.process.MakeObjectProcessor;
import com.cassius.spring.assembly.test.common.process.MockObjectProcessor;
import com.cassius.spring.assembly.test.common.process.MockOnBeanProcessor;
import com.cassius.spring.assembly.test.common.process.SpringBeanProcessor;
import com.cassius.spring.assembly.test.common.process.SpringMockProcessor;
import com.cassius.spring.assembly.test.common.setting.configure.ProcessorConfigure;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;

/**
 * AbstractJUnit4SpringAssemblyTests
 *
 * @author Cassius Cai
 * @version v 0.1 8/5/15 23:20 Exp $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ ServletTestExecutionListener.class,
                          DependencyInjectionTestExecutionListener.class,
                          DirtiesContextTestExecutionListener.class })
@ProcessorConfigure({ MakeObjectProcessor.class, MockObjectProcessor.class,
                      MockOnBeanProcessor.class, InjectIntoProcessor.class,
                      SpringBeanProcessor.class, SpringMockProcessor.class })
public abstract class AbstractJUnit4SpringAssemblyTests implements ApplicationContextAware {
    /**
     * Logger available to subclasses.
     */
    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * The {@link ApplicationContext} that was injected into this test instance
     * via {@link #setApplicationContext(ApplicationContext)}.
     */
    protected ApplicationContext applicationContext;

    /**
     * Set the {@link ApplicationContext} to be used by this test instance,
     * provided via {@link ApplicationContextAware} semantics.
     * @param applicationContext the ApplicationContext that this test runs in
     */
    @Override
    public final void setApplicationContext(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Before void.
     */
    @Before
    public void before() {
        SpringAssemblyTestUtil.before(this, applicationContext);
    }

    /**
     * After void.
     */
    @After
    public void after() {
        SpringAssemblyTestUtil.after(this, applicationContext);
    }
}
