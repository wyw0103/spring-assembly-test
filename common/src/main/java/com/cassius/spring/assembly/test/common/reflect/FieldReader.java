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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;

/**
 * Created by Cassius Cai on 1/25/15 03:08.
 */
public class FieldReader {
    /**
     * Logger available to subclasses.
     */
    protected final Log logger = LogFactory.getLog(getClass());
    /**
     * The Object.
     */
    private Object      object;
    /**
     * The Field.
     */
    private Field       field;

    /**
     * Instantiates a new Field reader.
     *
     * @param object the object
     * @param field the field
     */
    private FieldReader(Object object, Field field) {
        this.object = object;
        this.field = field;
    }

    /**
     * New instance.
     *
     * @param object the object
     * @param fieldName the field name
     * @return the field reader
     * @throws NoSuchFieldException the no such field exception
     */
    public static FieldReader newInstance(Object object,
                                          String fieldName) throws NoSuchFieldException {
        Class<?> clazz = object.getClass();
        while (clazz != Object.class) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                if (field != null) {
                    return newInstance(object, field);
                }
            } catch (NoSuchFieldException ignore) {
            }
            clazz = clazz.getSuperclass();
        }
        throw new NoSuchFieldException(fieldName + "not exist");
    }

    /**
     * New instance.
     *
     * @param object the object
     * @param field the field
     * @return the field reader
     */
    public static FieldReader newInstance(Object object, Field field) {
        return new FieldReader(object, field);
    }

    /**
     * Read object.
     *
     * @return the object
     * @throws Exception the exception
     */
    public Object read() throws Exception {
        field.setAccessible(true);
        Object value = field.get(object);
        if (logger.isDebugEnabled()) {
            logger.debug(
                LogFormatUtil.format("@ Read Field:", "In Class @ " + object.getClass().getName(),
                    "On Field @ " + field.getName(), "Of Value @ " + value));
        }
        return value;
    }

    /**
     * Gets field.
     *
     * @return the field
     */
    public Field getField() {
        return field;
    }

}
