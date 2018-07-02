/*
 * <p>copyright description</p>
 * @author <a href="mailto:egcs@21cn.com">Dongsheng Song</a>
 * $Id: LocalFileContent.java 1757 2005-03-23 05:51:35Z FORESEE.fanzhi $
 */

package com.ower.dsyz.common.vfs.provider.local;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import com.ower.dsyz.common.vfs.FileObject;
import com.ower.dsyz.common.vfs.provider.AbstractFileContent;

/**
 * <p>
 * class description
 * </p>
 * 
 * @author <a href="mailto:egcs@21cn.com">Dongsheng Song </a>
 * @version $Revision: 1757 $
 */
public class LocalFileContent extends AbstractFileContent {

	/** 本地文件系统文件. */
	private RandomAccessFile raf;

	/**
	 * 构造函数.
	 * 
	 * @param fs
	 *            文件系统
	 * @param file
	 *            文件名
	 * @param mode
	 *            打开方式
	 * @throws IOException
	 *             文件IO异常
	 */
	public LocalFileContent(File file, int mode) throws IOException {
		if (mode == FileObject.READ_WRITE) {
			if (file.getParentFile().exists() || file.getParentFile().mkdirs()) {
				raf = new RandomAccessFile(file, "rw");
			} else {
				throw new FileNotFoundException("无法打开或创建文件.");
			}
		} else {
			raf = new RandomAccessFile(file, "r");
		}
	}

	/** {@inheritDoc} */
	public void close() throws IOException {
		raf.close();
	}

	/** {@inheritDoc} */
	public int read(byte[] buf, int off, int len) throws IOException {
		return raf.read(buf, off, len);
	}

	/** {@inheritDoc} */
	public void write(byte[] buf, int off, int len) throws IOException {
		raf.write(buf, off, len);
	}

	/** {@inheritDoc} */
	public long getPointer() throws IOException {
		return raf.getFilePointer();
	}

	/** {@inheritDoc} */
	public void setPointer(long position) throws IOException {
		raf.seek(position);
	}

	/** {@inheritDoc} */
	public long getLength() throws IOException {
		return raf.length();
	}

	/** {@inheritDoc} */
	public void setLength(long length) throws IOException {
		raf.setLength(length);
	}

	/** {@inheritDoc} */
	public void flush() throws IOException {
		// RandomAccessFile do not need flush
	}

	@Override
	public void append(byte[] buf) throws IOException {
		raf.seek(raf.length());//将指针移动到文件尾 
		raf.write(buf);
	}
}