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

import com.cassius.spring.assembly.test.common.core.SpringAssemblyTest;
import com.cassius.spring.assembly.test.common.reflect.FieldReader;
import com.cassius.spring.assembly.test.common.reflect.FieldWriter;
import com.cassius.spring.assembly.test.common.setting.processor.InjectInto;
import com.cassius.spring.assembly.test.common.toolbox.ContextUtil;

import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;

import java.lang.reflect.Field;

/**
 * Created by Cassius Cai on 1/25/15 21:13.
 */
public class InjectIntoProcessor extends AbstractPstProcessor {
    /**
     * Need process before.
     *
     * @param instance the instance
     * @param field the field
     * @return the boolean
     */
    @Override
    protected boolean needProcessBefore(Object instance, Field field) {
        return field.isAnnotationPresent(InjectInto.class);
    }

    /**
     * Do process before.
     *
     * @param context the context
     * @param instance the instance
     * @param field the field
     * @throws Exception the exception
     */
    @Override
    protected void doProcessBefore(ApplicationContext context, Object instance,
                                   Field field) throws Exception {
        Object fieldValue = FieldReader.newInstance(instance, field).read();
        InjectInto injectInto = field.getAnnotation(InjectInto.class);
        String[] targets = injectInto.value();
        for (String target : targets) {
            Object targetFieldValue = FieldReader.newInstance(instance, target).read();
            FieldWriter.newInstance(targetFieldValue, field.getName(), fieldValue).write();
        }
    }

    /**
     * Need process after.
     *
     * @param instance the instance
     * @param field the field
     * @return the boolean
     */
    @Override
    protected boolean needProcessAfter(Object instance, Field field) {
        return field.isAnnotationPresent(InjectInto.class);
    }

    /**
     * Do process after.
     *
     * @param context the context
     * @param instance the instance
     * @param field the field
     * @throws Exception the exception
     */
    @Override
    protected void doProcessAfter(ApplicationContext context, Object instance,
                                  Field field) throws Exception {
        String contextName = ContextUtil.getSpringContextName(instance.getClass());
        SpringAssemblyTest.removeContext(ContextUtil.getMergedContextConfiguration(instance),
            DirtiesContext.HierarchyMode.EXHAUSTIVE);
    }
}
