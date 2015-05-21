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

import com.google.common.base.Strings;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Cassius Cai on 1/24/15 22:36.
 */
public class LogFormatUtil {
    /**
     * The constant SYMBOL_0.
     */
    private final static String SYMBOL_0    = "=";
    /**
     * The constant SYMBOL_2.
     */
    private final static String SYMBOL_2    = ">";
    /**
     * The constant SYMBOL_3.
     */
    private final static String SYMBOL_3    = "<";

    /**
     * The LINE _ lENGTH.
     */
    private final static int    LINE_LENGTH = 120;
    /**
     * The constant RETURN.
     */
    private final static String RETURN      = "\n";

    /**
     * The constant BLANK.
     */
    private final static String BLANK       = " ";

    /**
     * Format string.
     *
     * @param message the message
     * @param logs the logs
     * @return the string
     */
    public static String format(String message, String[] logs) {
        List<String> stringSet = new ArrayList<String>();
        stringSet.add(message);
        stringSet.addAll(Arrays.asList(logs));

        String[] result = new String[stringSet.size()];
        result = stringSet.toArray(result);
        return format(result);
    }

    /**
     * Format string.
     *
     * @param logs the logs
     * @return the string
     */
    public static String format(List<String> logs) {
        String logSep = formatBegLine();

        StringBuilder sb = new StringBuilder(logSep);
        for (String log : logs) {
            String LOG_LINE_BEG = RETURN + SYMBOL_2 + BLANK;
            if (log.length() < LINE_LENGTH - 2) {
                log = LOG_LINE_BEG + Strings.padEnd(log, LINE_LENGTH - 2, ' ');
            } else {

                String LOG_LINE_BREAK = "\n" + SYMBOL_3 + BLANK;
                log = LOG_LINE_BEG
                      + StringUtils.replace(insertBreakLineSymbol(log, LINE_LENGTH - 2), "\n",
                          LOG_LINE_BREAK);
            }
            sb.append(log);
        }
        sb.append(RETURN);
        return sb.toString();
    }

    /**
     * Format string.
     *
     * @param logs the logs
     * @return the string
     */
    public static String format(String... logs) {
        return format(Arrays.asList(logs));
    }

    /**
     * Insert break line symbol.
     *
     * @param string the string
     * @param length the length
     * @return the string
     */
    private static String insertBreakLineSymbol(String string, int length) {
        if (string.contains("\n")) {
            return string;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            if (i > 0 && i % length == 0) {
                stringBuilder.append(RETURN);
            }
            stringBuilder.append(string.charAt(i));

        }
        return stringBuilder.toString();
    }

    /**
     * Format beg line.
     *
     * @return the string
     */
    private static String formatBegLine() {
        StringBuilder sb = new StringBuilder(RETURN);
        for (int i = 0; i < (LINE_LENGTH); i++) {
            sb.append(SYMBOL_0);
        }
        return sb.toString();
    }
}