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

import java.util.Collection;
import java.util.Map;

/**
 * Created by Cassius Cai on 2/2/15 21:45.
 */
public class CollectionUtil {

    /**
     * Is not empty.
     *
     * @param map the map
     * @return boolean boolean
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * Is empty.
     *
     * @param map the map
     * @return boolean boolean
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    /**
     * Is not empty.
     *
     * @param collection the collection
     * @return boolean boolean
     */
    @SuppressWarnings("rawtypes")
    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    /**
     * Is empty.
     *
     * @param collection the collection
     * @return boolean boolean
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Collection collection) {
        return (collection == null || collection.isEmpty());
    }
}
