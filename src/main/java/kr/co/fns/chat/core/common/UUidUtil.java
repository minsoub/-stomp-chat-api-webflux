package kr.co.fns.chat.core.common;


import com.fasterxml.uuid.Generators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class UUidUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(UUidUtil.class);
	
	public UUidUtil() {
		init();
	}
	
	private void init() {
	}
	
	public String getUUid() 
	{
		String ret = "";
		
		UUID uuid = Generators.timeBasedGenerator().generate();
		ret = uuid.toString();
		
		return ret;
	}
	
	// 모바일 쿠폰 번호, 주문번호, 운송장 번호에 사용되는 숫자형태 16자리 생성
	public String getUUidNumber() 
	{
		String ret = "", temp = "", each = "", decimal = "";
		int i=0, num=0;
		
		String uid = getUUid();
		//logger.debug("uid > [{}]", uid);
		
		temp = uid.replace("-", "");
		
		temp = temp.trim();
		
		for(i=0; i<temp.length(); i++) 
		{
			each = Character.toString( temp.charAt(i) );
			num = Integer.parseInt( each, 16 );
			
			decimal = decimal + String.valueOf( num );  
		}
		
		//logger.debug("decimal > [{}]", decimal);
		
		// 숫자 4X4 => 16자리 => 모바일 쿠폰 번호, 주문번호, 운송장 번호 
		if( decimal.length() <= 16 ) 
		{
			ret = decimal;
		}
		else {
			ret = decimal.substring(0, 16);
		}
		
		//logger.debug("decimal > ret > [{}]", ret);
		
		return ret;
	}
	
	// 일반적 UUID 생성
	public String getUUidString() 
	{
		String ret = "", temp = "", each = "", decimal = "";
		
		String uid = getUUid();
		//logger.debug("uid > [{}]", uid);
		
		temp = uid.replace("-", "");
		
		temp = temp.trim();
		ret = temp;
		
		//logger.debug("ret > [{}]", ret);
		
		return ret;
	}
	
	public String getDateTimeMicro() 
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS");
		String curDateTimeMicro = sdf.format(new Date());
		
		return curDateTimeMicro;
	}
	
	// 거래 transaction UUID 생성
	public String getTransUUid() 
	{
		String ret = "";
		String uid = getUUidString();
		
		String curDateTimeMicro = getDateTimeMicro();
		
		ret =  uid + "_" + curDateTimeMicro;
		//ret = curDateTimeMicro + "_" + uid;
		
		return ret;
	}
	
	// CSRF 방지를 위한 상태 코드 생성 > gen_state_code (상태 코드 생성)
	public String getStateCode() 
	{
		SecureRandom random = new SecureRandom();
	    return new BigInteger(130, random).toString(32);
	}
	
	// auth_code: 인증 코드 > 생성
	public String getAuthCode() 
	{
		String ret = "";
		String uid = getStateCode();
		
		String curDateTimeMicro = getDateTimeMicro();
		
		ret =  uid + "_" + curDateTimeMicro;
		
		return ret;
	}
	
}
