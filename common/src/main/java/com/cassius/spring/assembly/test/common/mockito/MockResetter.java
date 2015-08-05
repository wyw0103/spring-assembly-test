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
package com.cassius.spring.assembly.test.common.mockito;

import com.cassius.spring.assembly.test.common.toolbox.LogFormatUtil;

import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * Created by Cassius Cai on 1/25/15 02:11.
 */
public class MockResetter {
    /**
     * The Logger.
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * The Test instance.
     */
    private Object       testInstance;
    /**
     * The Inject field.
     */
    private Field        injectField;

    /**
     * Instantiates a new Mock resetter.
     *
     * @param testInstance the test instance
     * @param injectField the inject field
     */
    public MockResetter(Object testInstance, Field injectField) {
        this.testInstance = testInstance;
        this.injectField = injectField;
    }

    /**
     * New instance.
     *
     * @param testInstance the test instance
     * @param injectField the inject field
     * @return the mock resetter
     */
    public static MockResetter newInstance(Object testInstance, Field injectField) {
        return new MockResetter(testInstance, injectField);
    }

    /**
     * Reset void.
     *
     * @throws Exception the exception
     */
    public void reset() throws Exception {
        injectField.setAccessible(true);
        Mockito.reset(injectField.get(testInstance));

        if (logger.isDebugEnabled()) {
            logger.debug(LogFormatUtil.format("@ Reset Mock Field:",
                "In Class @ " + testInstance.getClass().getName(),
                "On Field @ " + injectField.getName()));
        }
    }
}
