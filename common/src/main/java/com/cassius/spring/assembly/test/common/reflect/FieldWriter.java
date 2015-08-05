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
package com.cassius.spring.assembly.test.common.reflect;

import com.cassius.spring.assembly.test.common.toolbox.LogFormatUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * Created by Cassius Cai on 1/24/15 21:40.
 */
public class FieldWriter {
    /**
     * The Logger.
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * The Object.
     */
    private Object object;
    /**
     * The Field.
     */
    private Field  field;
    /**
     * The Value.
     */
    private Object value;

    /**
     * Instantiates a new Field writer.
     *
     * @param object the object
     * @param field the field
     * @param value the value
     */
    private FieldWriter(Object object, Field field, Object value) {
        this.object = object;
        this.field = field;
        this.value = value;
    }

    /**
     * New instance.
     *
     * @param object the object
     * @param field the field
     * @param value the value
     * @return the field writer
     */
    public static FieldWriter newInstance(Object object, Field field, Object value) {
        return new FieldWriter(object, field, value);
    }

    /**
     * New instance.
     *
     * @param object the object
     * @param fieldName the field name
     * @param value the value
     * @return the field writer
     * @throws NoSuchFieldException the no such field exception
     */
    public static FieldWriter newInstance(Object object, String fieldName,
                                          Object value) throws NoSuchFieldException {
        Class<?> clazz = object.getClass();
        while (clazz != Object.class) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                if (field != null) {
                    return new FieldWriter(object, field, value);
                }
            } catch (NoSuchFieldException ignore) {
            }
            clazz = clazz.getSuperclass();
        }
        throw new NoSuchFieldException(fieldName + "not exist");
    }

    /**
     * Write void.
     *
     * @throws Exception the exception
     */
    public void write() throws Exception {
        field.setAccessible(true);
        field.set(object, value);
        if (logger.isDebugEnabled()) {
            logger.debug(
                LogFormatUtil.format("@ Write Field:", "In Class @ " + object.getClass().getName(),
                    "On Field @ " + field.getName(), "Of Value @ " + value));
        }
    }
}
