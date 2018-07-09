package com.ower.dsyz.common.vfs.provider.wrapper;

import java.io.IOException;
import com.ower.dsyz.common.vfs.FileContent;

/**
 * 文件内容包装.
 * 
 * @author <a href="mailto:liji@163.com">liji</a>
 */
public abstract class FileContentWrapper implements FileContent {

	protected FileContent fileContent;

	public FileContentWrapper(FileContent fileContent) {
		this.fileContent = fileContent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileContent#close()
	 */
	@Override
	public void close() throws IOException {
		this.fileContent.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileContent#read(byte[], int, int)
	 */
	@Override
	public int read(byte[] buf, int off, int len) throws IOException {
		return this.fileContent.read(buf, off, len);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileContent#flush()
	 */
	@Override
	public void flush() throws IOException {
		this.fileContent.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileContent#write(byte[], int, int)
	 */
	@Override
	public void write(byte[] buf, int off, int len) throws IOException {
		this.fileContent.write(buf, off, len);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileContent#getPointer()
	 */
	@SuppressWarnings("deprecation")
	@Override
	public long getPointer() throws IOException {
		return this.fileContent.getPointer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileContent#setPointer(long)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void setPointer(long position) throws IOException {
		this.fileContent.setPointer(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileContent#getLength()
	 */
	@Override
	public long getLength() throws IOException {
		return this.fileContent.getLength();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileContent#setLength(long)
	 */
	@Override
	public void setLength(long length) throws IOException {
		this.fileContent.setLength(length);
	}

}
