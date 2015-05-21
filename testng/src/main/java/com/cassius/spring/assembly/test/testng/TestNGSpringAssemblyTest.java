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
package com.cassius.spring.assembly.test.testng;

import com.cassius.spring.assembly.test.common.core.SpringAssemblyTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * TestNGSpringAssemblyTest
 * <p/>
 *
 * @author Cassius Cai
 * @version v 0.1 3/1/15 17:47 Exp $
 */
public abstract class TestNGSpringAssemblyTest extends SpringAssemblyTest {

    /**
     * Before method.
     */
    @BeforeMethod
    public void beforeMethod() {
        super.before();
    }

    /**
     * After method.
     */
    @AfterMethod
    public void afterMethod() {
        super.after();
    }
}
