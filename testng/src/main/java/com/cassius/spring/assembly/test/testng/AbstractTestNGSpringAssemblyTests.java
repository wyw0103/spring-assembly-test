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
package com.cassius.spring.assembly.test.testng;

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
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

/**
 * AbstractTestNGSpringAssemblyTests
 *
 * @author Cassius Cai
 * @version v 0.1 8/5/15 23:15 Exp $
 */
@ProcessorConfigure({ MakeObjectProcessor.class, MockObjectProcessor.class,
                      MockOnBeanProcessor.class, InjectIntoProcessor.class,
                      SpringBeanProcessor.class, SpringMockProcessor.class })
public abstract class AbstractTestNGSpringAssemblyTests extends AbstractTestNGSpringContextTests {

    /**
     * The Logger.
     */
    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * Spring test context before test method.
     *
     * @param testMethod the test method
     * @throws Exception the exception
     */
    @BeforeMethod
    @Override
    protected void springTestContextBeforeTestMethod(Method testMethod) throws Exception {
        super.springTestContextBeforeTestMethod(testMethod);
        SpringAssemblyTestUtil.before(this, applicationContext);
    }

    /**
     * Method, Throwable) post-process}* the test method after the actual test has executed.
     *
     * @param testMethod the test method which has just been executed on the test instance
     * @throws Exception allows all exceptions to propagate
     */
    @AfterMethod
    @Override
    protected void springTestContextAfterTestMethod(Method testMethod) throws Exception {
        SpringAssemblyTestUtil.after(this, applicationContext);
        super.springTestContextAfterTestMethod(testMethod);
    }
}
