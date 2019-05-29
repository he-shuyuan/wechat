package com.ower.dsyz.common.core.holder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Description;
import org.springframework.core.NamedThreadLocal;

import com.ower.dsyz.common.core.request.CustomRequestAttributes;
/**
 * 
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2017年12月29日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
@Deprecated
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
