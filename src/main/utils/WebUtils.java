package main.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.UUID;

/**
 * @author : CWQ
 * @Description : 生成UUID
 * @date :2018-06-12
 **/
public class WebUtils {
    public static String makeID(){
        return UUID.randomUUID().toString();
    }
}
