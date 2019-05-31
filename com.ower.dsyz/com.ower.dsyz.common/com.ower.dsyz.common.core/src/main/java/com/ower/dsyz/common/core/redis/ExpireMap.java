package com.ower.dsyz.common.core.redis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class ExpireMap<K, V> {

	public Unit unit = Unit.MS;

	private ReentrantLock lock = new ReentrantLock();

	private Map<K, Long> keyMap = new HashMap<K, Long>();

	private Map<K, V> map;

	public ExpireMap(Map<K, V> map) {
		this.map = map;
	}

	public ExpireMap(Map<K, V> map, Unit unit) {
		this(map);
		this.unit = unit;
	}

	/**
	 * 加入带时间k-v
	 * 
	 * @param k
	 * @param v
	 * @param time
	 * @return
	 */
	public synchronized V put(K k, V v, Long time) {
		if (time != null) {

			this.addKepMap(k, time * this.unit.time + System.currentTimeMillis());
		}
		return map.put(k, v);
	}

	/**
	 * 加入k-v永久
	 * 
	 * @param k
	 * @param v
	 * @return
	 */
	public synchronized V put(K k, V v) {
		return this.put(k, v, null);
	}

	/**
	 * 获取v
	 * 
	 * @param k
	 * @return
	 */
	public synchronized V get(K k) {
		Long time = this.keyMap.get(k);
		if (time == null) {
			return this.map.get(k);
		} else if (time < System.currentTimeMillis()) {
			this.map.remove(k);
			this.removeKepMap(k);
			return null;
		} else {
			return this.map.get(k);
		}
	}

	public synchronized Set<K> keySet() {
		Set<K> key = new HashSet<K>();
		Set<K> removeKey = new HashSet<K>();
		for (K k : this.keyMap.keySet()) {
			if (this.keyMap.get(k) < System.currentTimeMillis()) {
				removeKey.add(k);
			} else {
				key.add(k);
			}
		}
		for (K k : removeKey) {
			this.removeKepMap(k);
			this.map.remove(k);
		}
		key.addAll(this.map.keySet());
		return key;
	}

	/**
	 * 移除k-v
	 * 
	 * @param k
	 * @return
	 */
	public synchronized V remove(K k) {
		Long v = removeKepMap(k);
		if (v != null && v < System.currentTimeMillis()) {
			this.map.remove(k);
			return null;
		} else {
			return this.map.remove(k);
		}
	}

	/**
	 * 是否包含k
	 * 
	 * @param k
	 * @return
	 */
	public synchronized Boolean containsKey(K k) {
		if (keyMap.containsKey(k) && keyMap.get(k) < System.currentTimeMillis()) {
			map.remove(k);
			return false;
		} else {
			return map.containsKey(k);
		}
	}

	/**
	 * 修改k时间
	 * 
	 * @param k
	 * @param time
	 * @return
	 */
	public synchronized Boolean expireKey(K k, Long time) {
		Long t = this.keyMap.get(k);
		V v = this.map.get(k);
		if (t == null && v == null) {
			return false;
		} else if (t == null) {
			addKepMap(k, time * this.unit.time + System.currentTimeMillis());
			return true;
		} else if (v == null) {
			removeKepMap(k);
			return false;
		} else if (t < System.currentTimeMillis()) {
			removeKepMap(k);
			this.map.remove(k);
			return false;
		} else {
			addKepMap(k, time * this.unit.time + System.currentTimeMillis());
			return true;
		}
	}

	private Long addKepMap(K k, Long time) {
		lock.lock();
		try {
			return keyMap.put(k, time);
		} finally {
			lock.unlock();
		}
	}

	private Long removeKepMap(K k) {
		lock.lock();
		try {
			return keyMap.remove(k);
		} finally {
			lock.unlock();
		}
	}

	enum Unit {

		MS(1L, "毫秒"), S(1000L, "秒"), MIN(1000 * 60L, "分钟");

		private Long time;
		@SuppressWarnings("unused")
		private String desc;

		private Unit(Long time, String desc) {
			this.time = time;
			this.desc = desc;
		}

	}

	@Override
	public String toString() {
		Set<K> set = this.keySet();
		Map<K, V> temp = new HashMap<K, V>();
		for (K k : set) {
			temp.put(k, this.map.get(k));
		}
		return temp.toString();
	}

}
