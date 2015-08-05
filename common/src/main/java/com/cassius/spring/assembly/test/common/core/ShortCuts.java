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
package com.cassius.spring.assembly.test.common.core;

import com.cassius.spring.assembly.test.common.toolbox.CollectionUtil;
import com.cassius.spring.assembly.test.common.toolbox.LogFormatUtil;
import com.google.common.collect.Lists;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cassius Cai on 2/2/15 21:30.
 */
public class ShortCuts extends Assertions {

    /**
     * Logger available to subclasses.
     */
    protected final Log logger = LogFactory.getLog(getClass());

    /**
     * Shortcut to get an array Object
     * @param params the params
     * @return object [ ]
     */
    public static Object[] array(Object... params) {
        return params;
    }

    /**
     * Print void.
     *
     * @param messages the messages
     */
    public void print(String... messages) {
        if (logger.isInfoEnabled()) {
            logger.info(LogFormatUtil.format(messages));
        }
    }

    /**
     * Print void.
     *
     * @param messages the messages
     */
    public void print(Object... messages) {
        if (logger.isInfoEnabled()) {
            List<String> stringList = new ArrayList<String>();
            for (Object obj : messages) {
                if (obj != null) {
                    stringList.add(obj.toString());
                }
            }
            logger.info(LogFormatUtil.format(stringList));
        }
    }

    /**
     * Print void.
     *
     * @param messages the messages
     */
    public void print(List<String> messages) {
        if (CollectionUtil.isEmpty(messages)) {
            return;
        }

        List<String> stringList = Lists.newArrayList();
        for (Object object : messages) {
            if (object == null) {
                continue;
            }
            stringList.add(object.toString());
        }
        if (logger.isInfoEnabled()) {
            logger.info(LogFormatUtil.format(stringList));
        }
    }
}
