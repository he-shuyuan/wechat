package com.ower.dsyz.common.vfs.provider.wrapper;

import java.io.IOException;
import java.util.Map;
import com.ower.dsyz.common.vfs.FileContent;
import com.ower.dsyz.common.vfs.FileObject;

/**
 * 文件对象包装.
 * 
 * @author <a href="mailto:liji@foresee.com.cn">liji</a>
 */
public abstract class FileObjectWrapper implements FileObject {

	protected FileObject fileObject;

	public FileObjectWrapper(FileObject fileObject) {
		this.fileObject = fileObject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileObject#getContent(int)
	 */
	@Override
	public FileContent getContent(int mode) throws IOException {
		return (FileContent) this.fileObject.getContent(mode);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileObject#create()
	 */
	@Override
	public boolean create() throws IOException {
		return this.fileObject.create();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileObject#mkdir()
	 */
	@Override
	public boolean mkdir() throws IOException {
		return this.fileObject.mkdir();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileObject#delete()
	 */
	@Override
	public boolean delete() throws IOException {
		return this.fileObject.delete();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileObject#isFile()
	 */
	@Override
	public boolean isFile() throws IOException {
		return this.fileObject.isFile();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileObject#isDirectory()
	 */
	@Override
	public boolean isDirectory() throws IOException {
		return this.fileObject.isDirectory();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileObject#getName()
	 */
	@Override
	public String getName() {
		return this.fileObject.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileObject#getPath()
	 */
	@Override
	public String getPath() {
		return this.fileObject.getPath();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileObject#getLastModified()
	 */
	@Override
	public long getLastModified() throws IOException {
		return this.fileObject.getLastModified();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileObject#getLength()
	 */
	@Override
	public long getLength() throws IOException {
		return this.fileObject.getLength();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileObject#getMetadata()
	 */
	@Override
	public Map<String, String> getMetadata() throws IOException {
		return this.fileObject.getMetadata();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileObject#getMetadata(java.lang.String)
	 */
	@Override
	public String getMetadata(String name) throws IOException {
		return this.fileObject.getMetadata(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileObject#setMetadata(java.util.Map)
	 */
	@Override
	public void setMetadata(Map<String, String> metadata) throws IOException {
		this.fileObject.setMetadata(metadata);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileObject#setMetadata(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void setMetadata(String name, String value) throws IOException {
		this.fileObject.setMetadata(name, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileObject#list()
	 */
	@Override
	public String[] list() throws IOException {
		return this.fileObject.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileObject#exists()
	 */
	@Override
	public boolean exists() throws IOException {
		return this.fileObject.exists();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.foresee.mobile.base.vfs.FileObject#copyTo(com.foresee.mobile.base.vfs.FileObject
	 * )
	 */
	@Override
	public void copyTo(FileObject target) throws IOException {
		this.fileObject.copyTo(target);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.foresee.mobile.base.vfs.FileObject#readFull()
	 */
	@Override
	public byte[] readFull() throws IOException {
		return this.fileObject.readFull();
	}

}
