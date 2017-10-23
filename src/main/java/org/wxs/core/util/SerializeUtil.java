package org.wxs.core.util;

import org.apache.log4j.Logger;

import java.io.*;

public class SerializeUtil {
	private static final Logger log = Logger.getLogger(SerializeUtil.class);

	/**
	 * 序列化
	 * 
	 * @author skyer
	 * @date 2017年4月18日 下午2:49:06
	 * @param obj
	 * @return
	 */
	public static byte[] serialize(Object obj) {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			return baos.toByteArray();
		} catch (IOException e) {
			log.error("IOException", e);
		} finally {
			try {
				if (null != baos) {
					baos.close();
				}
				if (null != oos) {
					oos.close();
				}
			} catch (IOException e) {
				log.error("IOException", e);
			}
		}
		return null;
	}
	/**
	 * 反序列化
	 * @author skyer
	 * @date 2017年4月18日 下午2:53:33
	 * @param value
	 * @return
	 */
	public static Object UnSerialize(byte[] value) {
		if(value == null || value.length == 0){
			return null;
		}
		ObjectInputStream ois = null;
		ByteArrayInputStream bais = null;
		try {
			bais = new ByteArrayInputStream(value);
			ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (IOException e) {
			log.error("IOException", e);
		} catch (ClassNotFoundException e) {
			log.error("ClassNotFoundException", e);
		} finally {
			try {
				if (null != ois) {
					ois.close();
				}
				if (null != bais) {
					bais.close();
				}
			} catch (IOException e) {
				log.error("IOException", e);
			}
		}
		return null;
	}
}
