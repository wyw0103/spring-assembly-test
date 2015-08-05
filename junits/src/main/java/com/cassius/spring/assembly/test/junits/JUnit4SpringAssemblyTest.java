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
package com.cassius.spring.assembly.test.junits;

import com.cassius.spring.assembly.test.common.core.SpringAssemblyTest;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;

/**
 * Created by Cassius Cai on 2/2/15 21:22.
 */
@RunWith(JUnitParamsRunner.class)
public abstract class JUnit4SpringAssemblyTest extends SpringAssemblyTest {
    /**
     * Before method.
     */
    @Before
    public void beforeMethod() {
        super.before();
    }

    /**
     * After method.
     */
    @After
    public void afterMethod() {
        super.after();
    }
}
