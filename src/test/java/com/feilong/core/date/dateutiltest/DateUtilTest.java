/*
 * Copyright (C) 2008 feilong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.core.date.dateutiltest;

import static java.util.Calendar.DAY_OF_MONTH;
import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.core.date.BaseDateUtilTest;
import com.feilong.core.date.DateUtil;

import static com.feilong.core.date.DateUtil.getFirstDateOfThisWeek;
import static com.feilong.core.date.DateUtil.isBefore;
import static com.feilong.core.date.DateUtil.toDate;

import static com.feilong.core.DatePattern.COMMON_DATE;
import static com.feilong.core.DatePattern.COMMON_DATE_AND_TIME;
import static com.feilong.core.DatePattern.COMMON_DATE_AND_TIME_WITHOUT_SECOND;
import static com.feilong.core.DatePattern.COMMON_DATE_AND_TIME_WITH_MILLISECOND;
import static com.feilong.core.DatePattern.TIMESTAMP_WITH_MILLISECOND;

/**
 * The Class DateUtilTest.
 * 
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 */
public class DateUtilTest extends BaseDateUtilTest{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtilTest.class);

    @Test
    public void testGetLastDateOfThisDay1(){

        logDate(DateUtils.ceiling(NOW, Calendar.DAY_OF_MONTH));
        logDate(DateUtils.round(NOW, Calendar.DAY_OF_MONTH));
        logDate(DateUtils.truncate(NOW, Calendar.DAY_OF_MONTH));
        LOGGER.debug(StringUtils.repeat("*", 20));
        logDate(DateUtils.ceiling(NOW, Calendar.MONTH));
        logDate(DateUtils.round(NOW, Calendar.MONTH));
        logDate(DateUtils.truncate(NOW, Calendar.MONTH));
    }

    /**
     * TestDateUtilTest.
     */
    @Test
    public void testDateUtil(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2014, Calendar.DECEMBER, 29);
        Date time = calendar.getTime();

        assertEquals("2014-12-29", DateUtil.toString(time, "yyyy-MM-dd"));
    }

    /**
     * TestDateUtilTest.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDateUtilTest8(){
        //jdk7- throw exception
        assertEquals("2015-12-29", DateUtil.toString(NOW, "YYYY-MM-dd"));
    }

    @Test
    public void testGetFirstDateOfThisDay1(){
        logDate(DateUtil.getFirstDateOfThisDay(new Date()));
        logDate(DateUtils.truncate(new Date(), DAY_OF_MONTH));
    }

    /**
     * Gets the first date of this week.
     */
    @Test
    public void testGetFirstDateOfThisWeek(){
        Date date = DateUtil.addDay(NOW, -2);
        LOGGER.debug("the param date:{}", DateUtil.toString(date, COMMON_DATE_AND_TIME_WITH_MILLISECOND));

        Date now3 = DateUtil.getFirstDateOfThisWeek(date);
        LOGGER.debug(DateUtil.toString(now3, COMMON_DATE_AND_TIME_WITH_MILLISECOND));

        LOGGER.debug("今天所在week 第一天:{}", DateUtil.toString(DateUtil.getFirstDateOfThisWeek(NOW), COMMON_DATE_AND_TIME_WITH_MILLISECOND));

        LOGGER.debug(
                        "getFirstDateOfThisWeek:{}",
                        DateUtil.toString(
                                        getFirstDateOfThisWeek(toDate("2014-01-01 05:00:00", COMMON_DATE_AND_TIME)),
                                        COMMON_DATE_AND_TIME_WITH_MILLISECOND));

    }

    /**
     * Test is before.
     */
    @Test
    public void testIsBefore(){
        assertEquals(true, isBefore(toDate("2011-03-05", COMMON_DATE), toDate("2011-03-10", COMMON_DATE)));
        assertEquals(false, isBefore(toDate("2011-05-01", COMMON_DATE), toDate("2011-04-01", COMMON_DATE)));
    }

    /**
     * Test string2 date.
     * 
     */
    @Test
    public void testToDate(){
        logDate(toDate("2016-06-28T01:21:12-0800", "yyyy-MM-dd'T'HH:mm:ssZ"));
        logDate(toDate("2016-06-28T01:21:12+0800", "yyyy-MM-dd'T'HH:mm:ssZ"));

        logDate(toDate("2016-02-33", COMMON_DATE));

        // 商品上线时间
        logDate(toDate("20130102140806000", TIMESTAMP_WITH_MILLISECOND));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testToDate1(){
        DateUtil.toDate("2016-06-30 15:36 ", COMMON_DATE_AND_TIME_WITHOUT_SECOND);
    }

    @Test
    public void testToDate2(){
        DateUtil.toDate(StringUtils.trimToEmpty("2016-06-30 15:36 "), COMMON_DATE_AND_TIME_WITHOUT_SECOND);
    }

    //***************************************************************************************************

    /**
     * Test is leap year.
     */
    @Test
    public void testIsLeapYear(){
        int year = -3;
        LOGGER.debug(new GregorianCalendar(-3, 1, 1).isLeapYear(year) + "");
        LOGGER.debug(DateUtil.isLeapYear(year) + "");
    }

    /**
     * Test get time length.
     */
    @Test
    public void testGetTimeLength(){
        LOGGER.debug((NOW.getTime() + "").length() + "");
    }

}