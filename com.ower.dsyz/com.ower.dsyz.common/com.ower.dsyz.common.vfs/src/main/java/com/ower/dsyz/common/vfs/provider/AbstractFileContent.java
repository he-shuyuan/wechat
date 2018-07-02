/*
 * <p>copyright description</p>
 * @author <a href="mailto:egcs@21cn.com">Dongsheng Song</a>
 * $Id: AbstractFileContent.java 1757 2005-03-23 05:51:35Z FORESEE.fanzhi $
 */

package com.ower.dsyz.common.vfs.provider;

import java.io.IOException;
import com.ower.dsyz.common.vfs.FileContent;

/**
 * <p>
 * class description
 * </p>
 * 
 * @author <a href="mailto:egcs@21cn.com">Dongsheng Song </a>
 * @version $Revision: 1757 $
 */
public abstract class AbstractFileContent implements FileContent {

    /**
     * 关闭文件.
     * 
     * @throws IOException IO异常
     */
    public void close() throws IOException {
        throw new IOException("Not Implement");
    }

    /**
     * 按字节读取文件内容.
     * 
     * @param buf 被填充的对象
     * @param off 偏移量
     * @param len 读取长度限制
     * @return 实际读取长度
     * @throws IOException IO异常
     */
    public int read(byte[] buf, int off, int len) throws IOException {
        throw new IOException("Not Implement");
    }

    /**
     * flush from buff.
     * 
     * @throws IOException IO异常
     */
    public void flush() throws IOException {
        throw new IOException("Not Implement");
    }

    /**
     * 按字节写入文件内容.
     * 
     * @param buf 将被写入的字节数组
     * @param off 偏移量
     * @param len 读取长度限制
     * @throws IOException IO异常
     */
    public void write(byte[] buf, int off, int len) throws IOException {
        throw new IOException("Not Implement");
    }

    /**
     * 获取当前游标位置.
     * 
     * @return 游标位置
     * @throws IOException IO异常
     */
    public long getPointer() throws IOException {
        throw new IOException("Not Implement");
    }

    /**
     * 设置当前游标位置.
     * 
     * @param position 游标位置
     * @throws IOException IO异常
     */
    public void setPointer(long position) throws IOException {
        throw new IOException("Not Implement");
    }

    /**
     * 获取当前文件长度.
     * 
     * @return 文件长度
     * @throws IOException IO异常
     */
    public long getLength() throws IOException {
        throw new IOException("Not Implement");
    }

    /**
     * 设置当前文件长度,可能截断文件.
     * 
     * @param length 文件长度
     * @throws IOException IO异常
     */
    public void setLength(long length) throws IOException {
        throw new IOException("Not Implement");
    }
}
