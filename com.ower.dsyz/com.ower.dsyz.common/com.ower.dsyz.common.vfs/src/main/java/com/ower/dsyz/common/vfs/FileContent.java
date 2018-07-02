package com.ower.dsyz.common.vfs;

import java.io.IOException;

/**
 * <p>
 * 文件内容操作接口
 * </p>
 * 
 * @author <a href="mailto:egcs@21cn.com">Dongsheng Song </a>
 * @version $Revision: 1757 $
 * <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *    					   李正涛     20150709    兼容省级集中版
 * </pre>
 */ 
public interface FileContent {
	/**
	 * 关闭文件.
	 * 
	 * @throws IOException 文件IO异常
	 */
	public void close() throws IOException;

	/**
	 * 读取文件
	 * 
	 * @param buf 字节缓冲区
	 * @param off 缓冲区偏移
	 * @param len 期望读取字节数
	 * @return 实际读取字节数
	 * @throws IOException 文件IO异常
	 */
	public int read(byte[] buf, int off, int len) throws IOException;

	/**
	 * 刷新文件
	 * 
	 * @throws IOException 文件IO异常
	 */
	public void flush() throws IOException;

	/**
	 * 写入文件
	 * 
	 * @param buf 字节缓冲区
	 * @param off 缓冲区偏移
	 * @param len 期望写字节数
	 * @throws IOException 文件IO异常
	 */
	public void write(byte[] buf, int off, int len) throws IOException;

	/**
	 * 取得文件指针位置
	 * 
	 * @return 游标位置
	 * @throws IOException 文件IO异常
	 */
	@Deprecated
	public long getPointer() throws IOException;

	/**
	 * 设置文件指针位置
	 * 
	 * @param position 从文件开始的字节计数
	 * @throws IOException 文件IO异常
	 */
	@Deprecated
	public void setPointer(long position) throws IOException;

	/**
	 * 取得文件长度
	 * 
	 * @return 文件长度,单位byte
	 * @throws IOException 文件IO异常
	 */
	public long getLength() throws IOException;

	/**
	 * 设置文件长度
	 * 
	 * @param length 文件长度，以字节计数
	 * @throws IOException 文件IO异常
	 */
	public void setLength(long length) throws IOException;
	
	/**
	 * 写入文件
	 * 
	 * @param buf 字节缓冲区
	 * @throws IOException 文件IO异常
	 */
	public void append(byte[] buf) throws IOException;
	

}
