package com.ower.dsyz.common.vfs;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import com.ower.dsyz.common.core.util.SpringContextUtil;

@Component
public class VFS {

	private static Log log = LogFactory.getLog(VFS.class);

	private Map<String, MountPoint> mounts = new LinkedHashMap<String, MountPoint>();

	private boolean inited = false;
	
	/**
	 * 获取文件信息.
	 * 
	 * @param filename 文件带路径名称
	 * @return 文件对象实例
	 * @throws IOException 文件IO异常
	 */
	public FileObject resolveFile(String filename) throws IOException {
		if (!inited) {
			this.init();
		}

		String target;
		MountPoint ms = null, t;

		Object[] mountList = mounts.values().toArray();

		for (int i = 0; i < mountList.length; i++) {
			t = (MountPoint) mountList[i];
			target = t.getTarget();
			if (filename.startsWith(target)) { // 之前已经按照长度排序
				ms = t;
				break;
			}
		}

		if (ms == null) {
			throw new IOException("Can't resole file '" + filename + "'");
		}

		try {
			FileSystem fs = ms.getFileSystem();

			return fs.resolveFile(filename);
		} catch (RuntimeException e) {
			throw new IOException(e);
		}
	}

	/**
	 * 从spring bean种初始化vfs挂载点.
	 */
	private synchronized void init() {
		if (!this.inited) {
			@SuppressWarnings("unchecked")
			List<MountPoint> mountPoints = (List<MountPoint>)SpringContextUtil.getBeansByClass(MountPoint.class);
			for (MountPoint mp : mountPoints) {
				mp.init();
			}
			mountPoints.addAll(this.mounts.values());

			Collections.sort(mountPoints);
			this.mounts.clear();
			for (MountPoint m : mountPoints) {
				this.mounts.put(m.getTarget(), m);
			}

			this.inited = true;
		}
	}

	/**
	 * 检查路径path是否是挂接点.
	 * 
	 * @param path 路径
	 * @return 是挂接点返回true,否则返回false
	 */
	public synchronized boolean isTarget(String path) {
		this.init();

		if (mounts.get(path) != null) {
			return true;
		}
		return false;
	}

	/**
	 * 检查路径path是否在挂接点为target的文件系统内.
	 * 
	 * @param target 文件系统挂接点
	 * @param path 路径
	 * @return 在文件系统内返回true，否则false
	 */
	public synchronized boolean isBond(String target, String path) {
		this.init();

		int score = target.length(), n;
		String target2;
		MountPoint t;

		if (!path.startsWith(target)) {
			return false;
		}

		Object[] mountList = mounts.values().toArray();

		for (int i = 0; i < mountList.length; i++) {
			t = (MountPoint) mountList[i];
			target2 = t.getTarget();
			if (path.startsWith(target2)) {
				n = target2.length();
				if (n > score) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 获取指定的挂载点.
	 * 
	 * @param target 挂在目标
	 * @return 挂载点信息
	 */
	public synchronized MountPoint getMountStruct(String target) {
		return mounts.get(target);
	}

	/**
	 * 在指定位置写入文件.
	 * 
	 * @param vfsPath vfs路径
	 * @param content 文件内容
	 * @return 成功标记
	 * @throws IOException 文件io异常
	 */
	public boolean setFileContent(String vfsPath, byte[] content) throws IOException {
		this.init();

		FileObject fo = null;
		try {
			fo = this.resolveFile(vfsPath);

			if (!fo.exists()) {
				if (!fo.create()) {
					throw new IOException("不能在指定位置"+vfsPath+"创建文件.");
				}
			}
		} catch (RuntimeException e) {
			throw new IOException(e);
		}

		FileContent fc = null;
		try {
			fc = fo.getContent(FileObject.READ_WRITE);
			fc.setLength(content.length);
			fc.write(content, 0, content.length);
			fc.flush();
			return true;
		} catch (RuntimeException e) {
			throw new IOException(e);
		} finally {
			if (fc != null) {
				try {
					fc.close();
				} catch (Exception ex) {
					log.debug(ex);
				}
			}
		}
	}

	/**
	 * 读取指定位置文件.
	 * 
	 * @param vfsPath vfs路径
	 * @return 文件内容
	 * @throws IOException 文件io异常
	 */
	public byte[] getFileContent(String vfsPath) throws IOException {
		this.init();

		try {
			return this.resolveFile(vfsPath).readFull();
		} catch (IOException ex) {
			log.debug("获取" + vfsPath + "的内容失败.");
			throw ex;
		}
	}

	/**
	 * 删除指定位置文件.
	 * 
	 * @param vfsPath vfs路径
	 * @return 成功标记
	 * @throws IOException 文件io异常
	 */
	public boolean deleteFile(String vfsPath) throws IOException {
		this.init();

		FileObject fo = null;
		try {
			fo = this.resolveFile(vfsPath);
			return fo.delete();
		} catch (RuntimeException e) {
			throw new IOException("在指定位置查找文件失败.", e);
		}
	}
	
	/**
	 * 在文件末尾加入指定内容.
	 * 
	 * @param vfsPath vfs路径
	 * @param content 添加的内容
	 * @return 成功标记
	 * @throws IOException 文件io异常
	 */
	public boolean appendFileContent(String vfsPath, byte[] content) throws IOException{
		this.init();
		
		FileObject fo = null;
		try {
			fo = this.resolveFile(vfsPath);
		} catch (RuntimeException e) {
			throw new IOException("在指定位置查找文件失败");
		}

		FileContent fc = null;
		try {
			fc = fo.getContent(FileObject.READ_WRITE);
			fc.append(content);
			fc.flush();
			return true;
		} catch (RuntimeException e) {
			throw new IOException(e);
		} finally {
			if (fc != null) {
				try {
					fc.close();
				} catch (Exception ex) {
					log.debug(ex);
				}
			}
		}
	}
	
}
