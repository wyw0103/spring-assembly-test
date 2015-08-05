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
package com.cassius.spring.assembly.test.common.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mockito.Mockito;
import org.mockito.internal.util.MockUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * The type Mockito spy wrapper bean post processor.
 */
public class SpringMockitoSpiedBeanReplaceProcessor implements BeanPostProcessor {

    /**
     * Logger available to subclasses.
     */
    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * Post process before initialization.
     *
     * @param bean the bean
     * @param beanName the bean name
     * @return the object
     * @throws BeansException the beans exception
     */
    public Object postProcessBeforeInitialization(Object bean,
                                                  String beanName) throws BeansException {
        return bean;
    }

    /**
     * Post process after initialization.
     *
     * @param bean the bean
     * @param beanName the bean name
     * @return the object
     * @throws BeansException the beans exception
     */
    public Object postProcessAfterInitialization(Object bean,
                                                 String beanName) throws BeansException {
        return createMockitoSpyBean(bean, beanName);
    }

    /**
     * Create mockito spy bean.
     *
     * @param bean the bean
     * @param beanName the bean name
     * @return the object
     */
    private Object createMockitoSpyBean(Object bean, String beanName) {
        Object spy = bean;
        MockUtil mockUtil = new MockUtil();
        if (mockUtil.isTypeMockable(bean.getClass()) && !mockUtil.isMock(bean)) {
            try {
                spy = Mockito.spy(bean);
                if (logger.isInfoEnabled()) {
                    logger.info("Create mockito spy success for bean:" + beanName);
                }
            } catch (Exception e) {
                return spy;
            }
        }
        return spy;
    }
}
