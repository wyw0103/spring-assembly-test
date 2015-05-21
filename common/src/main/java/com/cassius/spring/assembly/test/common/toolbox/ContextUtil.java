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

import com.cassius.spring.assembly.test.common.setting.configure.SpringAssemblyConfigure;
import com.cassius.spring.assembly.test.common.setting.configure.SpringContextConfigure;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Cassius Cai on 2/28/15 11:01.
 */
public class ContextUtil {
    /**
     * The constant DEFAULT_CONTEXT_NAME.
     */
    private final static String DEFAULT_CONTEXT_NAME = "_default";

    /**
     * Get spring context locations.
     *
     * @param testClass the test class
     * @return the string [ ]
     */
    public static Set<String> getSpringContextLocations(Class<?> testClass) {
        Set<String> files = new HashSet<String>();
        while (testClass != null) {
            SpringContextConfigure configure = testClass
                .getAnnotation(SpringContextConfigure.class);
            if (configure != null) {
                files.addAll(Arrays.asList(configure.value()));
            }
            testClass = testClass.getSuperclass();
        }
        return files;
    }

    /**
     * Gets spring context name.
     *
     * @param testClass the test class
     * @return the spring context name
     */
    public static String getSpringContextName(Class<?> testClass) {
        String contextName = DEFAULT_CONTEXT_NAME;
        SpringAssemblyConfigure springAssemblyConfigure = AnnotationUtils.findAnnotation(testClass,
            SpringAssemblyConfigure.class);
        if (springAssemblyConfigure.reuseSpringContext()) {
            return testClass.getName();
        }
        return contextName;
    }
}