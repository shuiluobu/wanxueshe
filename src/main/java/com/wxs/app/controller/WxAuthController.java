package com.wxs.app.controller;

import com.google.common.collect.ImmutableMap;
import com.wxs.app.service.WxService;
import com.wxs.cache.ICache;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.wxs.core.util.AES;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import java.util.Map;

/**
 * 微信用户认证相关
 * @author xiaoqiang
 *
 */
@RestController("wx")
public class WxAuthController{
	@Autowired
	private WxService wxService;
	@Autowired
	private ICache cache;

	/**
	 * 根据客户端传过来的code从微信服务器获取appid和session_key，然后生成3rdkey返回给客户端，后续请求客户端传3rdkey来维护客户端登录态
	 * @param wxCode	小程序登录时获取的code
	 * @return
	 */
	@RequestMapping(value = "/getSession", method = RequestMethod.GET, produces = "application/json")
	public Map<String,Object> createSssion(@RequestParam(required = true,value = "code")String wxCode){
		Map<String,Object> wxSessionMap = wxService.getWxSession(wxCode);
		if(null == wxSessionMap){
			return rtnParam(50010, null);
		}
		//获取异常
		if(wxSessionMap.containsKey("errcode")){
			return rtnParam(50020, null);
		}
		String wxOpenId = (String)wxSessionMap.get("openid");
		String wxSessionKey = (String)wxSessionMap.get("session_key");
		System.out.println(wxSessionKey);
		Integer expires = Integer.parseInt(String.valueOf(wxSessionMap.get("expires_in")));
		String thirdSession = wxService.create3rdSession(wxOpenId, wxSessionKey, expires);
		return rtnParam(0, ImmutableMap.of("sessionId",thirdSession));
	}

	/**
	 * 验证用户信息完整性
	 * @param rawData	微信用户基本信息
	 * @param signature	数据签名
	 * @param sessionId	会话ID
	 * @return
	 */

	@RequestMapping(value = "/checkUserInfo", method = RequestMethod.GET, produces = "application/json")
	public Map<String,Object> checkUserInfo(@RequestParam(required = true,value = "rawData")String rawData,
			@RequestParam(required = true,value = "signature")String signature,
			@RequestParam(required = true,defaultValue = "sessionId")String sessionId){
		Object wxSessionObj = cache.getCache(sessionId);
		if(null == wxSessionObj){
			return rtnParam(40008, null);
		}
		String wxSessionStr = (String)wxSessionObj;
		String sessionKey = wxSessionStr.split("#")[0];
		StringBuffer sb = new StringBuffer(rawData);
		sb.append(sessionKey);

		byte[] encryData = DigestUtils.sha1(sb.toString());
		byte[] signatureData = signature.getBytes();
		Boolean checkStatus = Arrays.equals(encryData, signatureData);
		return rtnParam(0, ImmutableMap.of("checkPass", checkStatus));
	}

	/**
	 * 获取用户openId和unionId数据(如果没绑定微信开放平台，解密数据中不包含unionId)
	 * @param encryptedData 加密数据
	 * @param iv			加密算法的初始向量	
	 * @param sessionId		会话ID
	 * @return
	 */
	@RequestMapping(value = "/decodeUserInfo", method = RequestMethod.GET, produces = "application/json")
	public Map<String,Object> decodeUserInfo(@RequestParam(required = true,value = "encryptedData")String encryptedData,
			@RequestParam(required = true,defaultValue = "iv")String iv,
			@RequestParam(required = true,defaultValue = "sessionId")String sessionId){
		System.out.println(encryptedData);
		System.out.println(iv);
		//从缓存中获取session_key
		Object wxSessionObj = cache.getCache(sessionId);
		if(null == wxSessionObj){
			return rtnParam(40008, null);
		}
		String wxSessionStr = (String)wxSessionObj;
		String sessionKey = wxSessionStr.split("#")[0];

		try {
			AES aes = new AES();
			byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
			if(null != resultByte && resultByte.length > 0){
				String userInfo = new String(resultByte, "UTF-8");
				return rtnParam(0, userInfo);
			}
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return rtnParam(50021, null);
	}

	/**
	 * 接口数据返回
	 * @param errorCode
	 * @param data
	 * @return
	 */
	protected Map<String,Object> rtnParam(Integer errorCode,Object data) {
		//正常的业务逻辑
		if(errorCode == 0){
			return ImmutableMap.of("errorCode", errorCode,"data", (data == null)? new Object() : data);
		}else{
			return ImmutableMap.of("errorCode", errorCode, "msg", "");
		}
	}
}
