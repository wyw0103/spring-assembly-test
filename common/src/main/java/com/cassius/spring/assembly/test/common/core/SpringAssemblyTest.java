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
package com.cassius.spring.assembly.test.common.core;

import com.cassius.spring.assembly.test.common.process.InjectIntoProcessor;
import com.cassius.spring.assembly.test.common.process.MakeObjectProcessor;
import com.cassius.spring.assembly.test.common.process.MockObjectProcessor;
import com.cassius.spring.assembly.test.common.process.MockOnBeanProcessor;
import com.cassius.spring.assembly.test.common.process.SpringBeanProcessor;
import com.cassius.spring.assembly.test.common.process.SpringMockProcessor;
import com.cassius.spring.assembly.test.common.setting.configure.ProcessorConfigure;
import com.cassius.spring.assembly.test.common.setting.configure.SpringAssemblyConfigure;
import com.cassius.spring.assembly.test.common.spring.ContextCache;
import com.cassius.spring.assembly.test.common.toolbox.ContextUtil;
import com.cassius.spring.assembly.test.common.toolbox.LogFormatUtil;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.MergedContextConfiguration;

/**
 * TestNG Unit Test Engine
 * <p/>
 * Created by Cassius Cai on 1/24/15 19:03.
 */
@ProcessorConfigure({ MakeObjectProcessor.class, MockObjectProcessor.class,
                      MockOnBeanProcessor.class, InjectIntoProcessor.class,
                      SpringBeanProcessor.class, SpringMockProcessor.class })
@SpringAssemblyConfigure
public class SpringAssemblyTest extends ShortCuts {

    /**
     * The constant contextMap.
     */
    private static final ContextCache contextCache = new ContextCache();

    /**
     * Gets context cache.
     *
     * @return the context cache
     */
    public static ContextCache getContextCache() {
        return contextCache;
    }

    /**
     * Before test execution
     *
     * @throws Exception
     */
    public void before() {
        try {
            SpringAssemblyTestUtil.before(this, getSpringContext());
        } catch (Exception e) {
            fail(e.getMessage(), e);
        }
    }

    /**
     * Init Spring Context
     * @return the spring context
     */
    private ApplicationContext getSpringContext() {

        String[] configurationLocations = ContextUtil.getContextConfiguration(this);
        MergedContextConfiguration contextConfiguration = new MergedContextConfiguration(getClass(),
            configurationLocations, null, null, null);
        if (!contextCache.contains(contextConfiguration)) {
            ApplicationContext context = new ClassPathXmlApplicationContext(configurationLocations);
            if (logger.isInfoEnabled()) {
                logger
                    .info(LogFormatUtil.format("@@ Init Spring Context: " + getClass().getName()));
                logger.info(LogFormatUtil.format("@@ Loaded Spring Files: ",
                    configurationLocations.toString()));
                logger.info(LogFormatUtil.format("@@ Loaded Spring Beans: ",
                    context.getBeanDefinitionNames()));
            }
            contextCache.put(contextConfiguration, context);
        }
        return contextCache.get(contextConfiguration);
    }

    /**
     * After test method execution
     *
     * @throws Exception
     */
    public void after() {
        SpringAssemblyTestUtil.after(this, getSpringContext());
    }

}
