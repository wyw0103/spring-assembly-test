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

import com.cassius.spring.assembly.test.common.process.AbstractExeProcessor;
import com.cassius.spring.assembly.test.common.process.AbstractPreProcessor;
import com.cassius.spring.assembly.test.common.process.AbstractPstProcessor;
import com.cassius.spring.assembly.test.common.process.AnnotationProcessor;
import com.cassius.spring.assembly.test.common.setting.configure.ProcessorConfigure;
import com.cassius.spring.assembly.test.common.toolbox.ReflectionUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.assertj.core.api.Assertions;
import org.mockito.Mockito;
import org.mockito.internal.util.MockUtil;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * SpringAssemblyTestUtil
 *
 * @author Cassius Cai
 * @version v 0.1 8/5/15 22:14 Exp $
 */
public class SpringAssemblyTestUtil {
    /**
     * Logger available to subclasses.
     */
    protected final static Log logger = LogFactory.getLog(SpringAssemblyTestUtil.class);

    /**
     * Before void.
     *
     * @param testInstance the test instance
     * @param applicationContext the application context
     */
    public static void before(Object testInstance, ApplicationContext applicationContext) {
        try {
            AnnotationProcessorsConfigure annotationProcessorsConfigure = new AnnotationProcessorsConfigure(
                testInstance).invoke();
            for (AnnotationProcessor processor : annotationProcessorsConfigure.getPreProcessors()) {
                processor.before(applicationContext, testInstance);
            }
            for (AnnotationProcessor processor : annotationProcessorsConfigure.getExeProcessors()) {
                processor.before(applicationContext, testInstance);
            }
            for (AnnotationProcessor processor : annotationProcessorsConfigure.getPstProcessors()) {
                processor.before(applicationContext, testInstance);
            }

            // reset mockito behavior
            for (String beanName : applicationContext.getBeanDefinitionNames()) {
                Object bean = null;
                try {
                    bean = applicationContext.getBean(beanName);
                } catch (Exception e) {
                    continue;
                }

                if (new MockUtil().isMock(bean)) {
                    Mockito.reset(bean);
                    logger.info("Reset mockito object: " + beanName);
                }
            }
        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e);
        }
    }

    /**
     * After void.
     *
     * @param testInstance the test instance
     * @param applicationContext the application context
     */
    public static void after(Object testInstance, ApplicationContext applicationContext) {
        try {
            AnnotationProcessorsConfigure annotationProcessorsConfigure = new AnnotationProcessorsConfigure(
                testInstance).invoke();
            for (AnnotationProcessor processor : annotationProcessorsConfigure.getPreProcessors()) {
                processor.after(applicationContext, testInstance);
            }
            for (AnnotationProcessor processor : annotationProcessorsConfigure.getExeProcessors()) {
                processor.after(applicationContext, testInstance);
            }
            for (AnnotationProcessor processor : annotationProcessorsConfigure.getPstProcessors()) {
                processor.after(applicationContext, testInstance);
            }

            // reset mockito behavior
            for (String beanName : applicationContext.getBeanDefinitionNames()) {
                Object bean = null;
                try {
                    bean = applicationContext.getBean(beanName);
                } catch (Exception e) {
                    continue;
                }

                if (new MockUtil().isMock(bean)) {
                    Mockito.reset(bean);
                    logger.info("Reset mockito object: " + beanName);
                }
            }
        } catch (Exception e) {
            Assertions.fail(e.getMessage(), e);
        }
    }

    /**
     * The type Annotation processors configure.
     */
    private static class AnnotationProcessorsConfigure {
        /**
         * The Test instance.
         */
        private Object                   testInstance;
        /**
         * The Pre processors.
         */
        private Set<AnnotationProcessor> preProcessors;
        /**
         * The Exe processors.
         */
        private Set<AnnotationProcessor> exeProcessors;
        /**
         * The Pst processors.
         */
        private Set<AnnotationProcessor> pstProcessors;

        /**
         * Instantiates a new Annotation processors configure.
         *
         * @param testInstance the test instance
         */
        public AnnotationProcessorsConfigure(Object testInstance) {
            this.testInstance = testInstance;
        }

        /**
         * Gets pre processors.
         *
         * @return the pre processors
         */
        public Set<AnnotationProcessor> getPreProcessors() {
            return preProcessors;
        }

        /**
         * Gets exe processors.
         *
         * @return the exe processors
         */
        public Set<AnnotationProcessor> getExeProcessors() {
            return exeProcessors;
        }

        /**
         * Gets pst processors.
         *
         * @return the pst processors
         */
        public Set<AnnotationProcessor> getPstProcessors() {
            return pstProcessors;
        }

        /**
         * Invoke annotation processors configure.
         *
         * @return the annotation processors configure
         * @throws InstantiationException the instantiation exception
         * @throws InstantiationException the instantiation exception
         */
        public AnnotationProcessorsConfigure invoke() throws InstantiationException,
                                                      IllegalAccessException {
            Set<Class<? extends AnnotationProcessor>> configuredProcessors = new HashSet<Class<? extends AnnotationProcessor>>();
            Class<?> clazz = testInstance.getClass();
            while (clazz != null) {
                ProcessorConfigure processorConfigure = clazz
                    .getAnnotation(ProcessorConfigure.class);
                if (processorConfigure != null) {
                    configuredProcessors.addAll(Arrays.asList(processorConfigure.value()));
                }
                clazz = clazz.getSuperclass();
            }
            preProcessors = new HashSet<AnnotationProcessor>();
            exeProcessors = new HashSet<AnnotationProcessor>();
            pstProcessors = new HashSet<AnnotationProcessor>();
            for (Class<? extends AnnotationProcessor> processor : configuredProcessors) {
                if (ReflectionUtil.instanceOrSubclassOf(processor, AbstractPreProcessor.class)) {
                    preProcessors.add(processor.newInstance());
                }
                if (ReflectionUtil.instanceOrSubclassOf(processor, AbstractExeProcessor.class)) {
                    exeProcessors.add(processor.newInstance());
                }
                if (ReflectionUtil.instanceOrSubclassOf(processor, AbstractPstProcessor.class)) {
                    pstProcessors.add(processor.newInstance());
                }

            }
            return this;
        }
    }
}
