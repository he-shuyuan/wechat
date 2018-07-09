/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.common.core.exception;

import com.ower.dsyz.common.core.constant.ErrorCodeConstants;

/**
 * 
 * <pre>
 * 统一异常处理
 * </pre>
 *
 * @author HeShuyuan@163.com
 * @date 2018年1月2日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class CustomRunTimeException
  extends RuntimeException
{
  private static final long serialVersionUID = 6358803642758332780L;
  private String errorCode;
  private String errorMsg;
  
  public String getErrorCode()
  {
    return this.errorCode;
  }
  
  public String getErrorMsg()
  {
    return this.errorMsg;
  }
  
  public CustomRunTimeException(String errorMsg)
  {
    this(ErrorCodeConstants.DEFAULT_ERROR, errorMsg, null);
  }
  
  public CustomRunTimeException(String errorCode, String errorMsg)
  {
    this(errorCode, errorMsg, null);
  }
  
  public CustomRunTimeException(String errorCode, String errorMsg, Throwable t)
  {
    super(errorMsg, t);
	this.errorCode = errorCode;
    this.errorMsg = errorMsg;
  }
  
  public CustomRunTimeException(String errorCode, Throwable t)
  {
    super(errorCode, t);
    this.errorCode = errorCode;
  }
}

