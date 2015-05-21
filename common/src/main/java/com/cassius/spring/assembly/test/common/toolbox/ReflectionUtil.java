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
package com.cassius.spring.assembly.test.common.toolbox;

import java.lang.annotation.Annotation;

/**
 * Created by Cassius Cai on 1/24/15 20:17.
 */
public class ReflectionUtil {

    /**
     * If thisClass is instance or subclass of thatClass
     *
     * @param thisClass the this class
     * @param thatClass the that class
     * @return boolean boolean
     */
    public static boolean instanceOrSubclassOf(Class<?> thisClass, Class<?> thatClass) {
        if (thisClass == null || thatClass == null) {
            return false;
        }

        while (thisClass != Object.class) {
            if (thisClass == thatClass) {
                return true;
            }
            thisClass = thisClass.getSuperclass();
        }

        return false;
    }

    /**
     * Is class annotated with.
     *
     * @param annotationClazz the annotation clazz
     * @param clazz the clazz
     * @return boolean boolean
     */
    public static boolean isClassAnnotatedWith(Class<? extends Annotation> annotationClazz,
                                               Class<?> clazz) {
        while (clazz != Object.class) {
            if (clazz.isAnnotationPresent(annotationClazz)) {
                return true;
            }
            clazz = clazz.getSuperclass();
        }
        return false;
    }
}
