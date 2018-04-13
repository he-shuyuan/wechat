package com.ower.hsy.common.core.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.NamedThreadLocal;
/**
 * 
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2017年12月29日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class CustomRequestContextHolder
{
  private static final ThreadLocal<CustomRequestAttributes> requestAttributesHolder = new NamedThreadLocal<>("custom request attributes");
  
  public static void initRequestAttributes(HttpServletRequest request, HttpServletResponse response)
  {
    requestAttributesHolder.remove();
    CustomRequestAttributes requestAttributes = new CustomRequestAttributes(request, response);
    requestAttributesHolder.set(requestAttributes);
  }
  
  public static CustomRequestAttributes getRequestAttributes()
  {
    return (CustomRequestAttributes)requestAttributesHolder.get();
  }
  
  public static boolean existsRequestAttributes()
  {
    return requestAttributesHolder.get() != null;
  }
  
  public static void resetRequestAttributes()
  {
    requestAttributesHolder.remove();
  }
  
}
