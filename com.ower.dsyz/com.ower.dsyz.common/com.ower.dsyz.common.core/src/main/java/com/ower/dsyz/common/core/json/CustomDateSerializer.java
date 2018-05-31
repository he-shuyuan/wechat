/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.ower.dsyz.common.core.json;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年1月19日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

import java.io.IOException;  
import java.text.SimpleDateFormat;  
import java.util.Date;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;  
/**
 *  
 * <pre>
 * 自定义返回JSON 数据格中日期格式化处理 
 * </pre>
 *
 * @author HeShuyuan@foresee.com.cn
 * @date 2018年1月19日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class CustomDateSerializer extends JsonSerializer<Date> {  
  
    @Override  
    public void serialize(Date value,   
            JsonGenerator jsonGenerator,   
            SerializerProvider provider)  
            throws IOException, JsonProcessingException {  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        jsonGenerator.writeString(sdf.format(value));  
    }  
}  

