package com.ower.dsyz.common.vfs;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MountPoint implements Comparable<MountPoint> {
	
	private static final Log log = LogFactory.getLog(MountPoint.class);

	/** 挂载点位置. */
	private String target = "/";
	private FileSystem fileSystem = null;

	/** 附加属性. */
	private Map<String, String> parameter = new HashMap<String, String>();

	/**
	 * 用于挂载点目标长短排序
	 * 
	 * @param o 比较对象
	 * @return 用于排序的int结果
	 */
	@Override
	public int compareTo(MountPoint o) {
		return o.getTarget().compareTo(this.getTarget());
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof MountPoint) {
			return this.getTarget().equals(((MountPoint) o).getTarget());
		}
		return false;
	}

	public FileSystem getFileSystem() {
		return fileSystem;
	}

	/**
	 * 获取一项指定属性的值
	 * 
	 * @param name 属性名
	 * @return 属性值
	 */
	public String getParameter(String name) {
		return parameter.get(name);
	}

	/**
	 * get target
	 * 
	 * @return target
	 */
	public String getTarget() {
		return target;
	}

	@Override
	public int hashCode() {
		return this.target.hashCode();
	}

	public void init() {
		log.debug("VFS mount " + target + " on " + fileSystem.getClass());
		this.fileSystem.setTarget(this.target);
		this.fileSystem.setParameters(this.parameter);
	}

	public void setFileSystem(FileSystem fileSystem) {
		this.fileSystem = fileSystem;
	}

	/**
	 * 设置一项指定的附加属性
	 * 
	 * @param name 属性名
	 * @param value 属性值
	 */
	public void setParameter(String name, String value) {
		if (value != null) {
			parameter.put(name, value);
		} else {
			parameter.remove(name);
		}
	}

	/**
	 * set attributes
	 * 
	 * @param attributes
	 *            attributes
	 */
	public void setParameters(Map<String, String> attributes) {
		parameter.putAll(attributes);
	}

	/**
	 * set target
	 * 
	 * @param target
	 *            target
	 */
	public void setTarget(String target) {
		if ((target == null) || (target.trim().length() < 1)) {
			throw new IllegalArgumentException("invalid mount target");
		}

		if (target.charAt(0) != '/') {
			target = '/' + target;
		}
		if (target.charAt(target.length() - 1) != '/') {
			target += '/';
		}

		this.target = target;
	}

}
