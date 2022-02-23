/**
 * @projectName: cloudoffice
 * @package: com.guojian.server.utils
 * @className: TimeUtils
 * @author: Substatham
 * @date: 2022/2/23 15:45
 * @version: 1.0
 */

package com.guojian.server.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TimeUtils {

    public static LocalDate date2LocalDate(Date date) {
        if(null == date) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
