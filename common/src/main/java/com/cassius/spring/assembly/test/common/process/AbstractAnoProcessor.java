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
package com.cassius.spring.assembly.test.common.process;

import org.springframework.context.ApplicationContext;

import java.lang.reflect.Field;

/**
 * Created by Cassius Cai on 1/25/15 02:30.
 */
public abstract class AbstractAnoProcessor implements AnnotationProcessor {
    /**
     * Process before test execution
     *
     * @param context Spring Context
     * @param instance Test instance
     * @throws Exception the exception
     */
    @Override
    public void before(ApplicationContext context, Object instance) throws Exception {
        Class<?> clazz = instance.getClass();
        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (needProcessBefore(instance, field)) {
                    doProcessBefore(context, instance, field);
                }
            }
            clazz = clazz.getSuperclass();
        }
    }

    /**
     * Need process before.
     *
     * @param instance the instance
     * @param field the field
     * @return the boolean
     */
    protected abstract boolean needProcessBefore(Object instance, Field field);

    /**
     * Do process before.
     *
     * @param context the context
     * @param instance the instance
     * @param field the field
     * @throws Exception the exception
     */
    protected abstract void doProcessBefore(ApplicationContext context, Object instance,
                                            Field field) throws Exception;

    /**
     * Process after test execution
     *
     * @param context Spring Context
     * @param instance Test instance
     * @throws Exception the exception
     */
    @Override
    public void after(ApplicationContext context, Object instance) throws Exception {
        Class<?> clazz = instance.getClass();
        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (needProcessAfter(instance, field)) {
                    doProcessAfter(context, instance, field);
                }
            }
            clazz = clazz.getSuperclass();
        }
    }

    /**
     * Need process after.
     *
     * @param instance the instance
     * @param field the field
     * @return the boolean
     */
    protected abstract boolean needProcessAfter(Object instance, Field field);

    /**
     * Do process after.
     *
     * @param context the context
     * @param instance the instance
     * @param field the field
     * @throws Exception the exception
     */
    protected abstract void doProcessAfter(ApplicationContext context, Object instance,
                                           Field field) throws Exception;
}
