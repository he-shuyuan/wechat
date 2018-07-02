package com.ower.dsyz.common.vfs;

import java.io.IOException;
import java.util.Map;
/**
* <pre>
* 修改记录
*    修改后版本:     修改人：  修改日期:     修改内容:
*    					   李正涛     20150709    兼容省级集中版
* </pre>
*/
public interface FileObject {

    /** 文件打开方式-只读. */
    public static final int READ_ONLY = 0;

    /** 文件打开方式-读写. */
    public static final int READ_WRITE = 1;

    /**
     * 获取文件内容操作对象实例.
     * 
     * @param mode 文件打开方式READ_ONLY/READ_WRITE
     * @return 文件内容对象
     * @throws IOException 任何IO异常
     */
    public FileContent getContent(int mode) throws IOException;

    /**
     * 创建新文件.
     * 
     * @return 成功标记
     * @throws IOException 文件IO异常
     */
    public boolean create() throws IOException;

    /**
     * 创建目录
     * 
     * @return 成功标记
     * @throws IOException 文件IO异常
     */
    public boolean mkdir() throws IOException;

    /**
     * 删除目录或者文件
     * 
     * @return 成功标记
     * @throws IOException 文件IO异常
     */
    public boolean delete() throws IOException;

    /**
     * 是否文件
     * 
     * @return 成功标记
     * @throws IOException 文件IO异常
     */
    public boolean isFile() throws IOException;

    /**
     * 是否目录
     * 
     * @return 成功标记
     * @throws IOException 文件IO异常
     */
    public boolean isDirectory() throws IOException;

    /**
     * 取得没有路径的文件名称
     * 
     * @return 最简文件名
     */
    public String getName();

    /**
     * 取得包含全VFS路径的文件名称
     * 
     * @return 文件完整路径
     */
    public String getPath();

    /**
     * 取得文件最近修改的(java)时间
     * 
     * @return 最后修改时间, long类型
     * @throws IOException 文件IO异常
     */
    public long getLastModified() throws IOException;

    /**
     * 取得文件长度
     * 
     * @return 文件长度,单位byte
     * @throws IOException 文件IO异常
     */
    public long getLength() throws IOException;

    /**
     * 取得文件元数据.
     * 
     * @return 附加属性表
     * @throws IOException 文件IO异常
     */
    public Map<String, String> getMetadata() throws IOException;

    /**
     * 取得文件元数据
     * 
     * @param name 附加属性名
     * @return 附加属性值
     * @throws IOException 文件IO异常
     */
    public String getMetadata(String name) throws IOException;

    /**
     * 设置文件元数据
     * 
     * @param metadata 附加属性表
     * @throws IOException 文件IO异常
     */
    public void setMetadata(Map<String, String> metadata) throws IOException;

    /**
     * 设置文件元数据
     * 
     * @param name 附加属性名
     * @param value 附加属性值
     * @throws IOException 文件IO异常
     */
    public void setMetadata(String name, String value) throws IOException;

    /**
     * 如果此对象是目录，那么枚举其中的文件和目录，返回的是VFS全路径字符串数组
     * 
     * @return 文件名数组
     * @throws IOException 文件IO异常
     */
    public String[] list() throws IOException;

    /**
     * 检查此对象是否物理存在
     * 
     * @return 文件是否存在
     * @throws IOException 文件IO异常
     */
    public boolean exists() throws IOException;

    /**
     * 如果此对象是文件，那么将它的内容复制到新文件中
     * 
     * @param filename 目标文件名
     * @throws IOException 文件IO异常
     */
    public void copyTo(FileObject target) throws IOException;

    /**
     * 读取文件全部内容
     * 
     * @return 实际读取字节内容
     * @throws IOException 文件IO异常
     */
    public byte[] readFull() throws IOException;

}
