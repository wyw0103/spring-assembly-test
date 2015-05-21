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

/**
 * Created by Cassius Cai on 1/24/15 17:55.
 */
public interface AnnotationProcessor {

    /**
     * Process before test execution
     *
     * @param context Spring Context
     * @param instance Test instance
     * @throws Exception the exception
     */
    public void before(ApplicationContext context, Object instance) throws Exception;

    /**
     * Process after test execution
     *
     * @param context Spring Context
     * @param instance Test instance
     * @throws Exception the exception
     */
    public void after(ApplicationContext context, Object instance) throws Exception;
}
