package com.connice.common.util;

import java.util.Random;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @Author: WenQiangRao
 * @Description:   公共的utils
 * @Date: Created in 10:26 2022/10/18
 * Modified By:
 **/
public class CommonUtils {

    /**
     * 随机生成6位验证码
     * @return
     */
    public static String code(){
        Random random = new Random();
        String code = "";
        for (int i=0;i<6;i++){
            code+= random.nextInt(10);//产生0-9的随机验证码
        }
        return code;
    }

    /**
     * 发送短信
     * @param to
     * @param code
     */
    public static boolean send(String to, String code) {
        String accessKeyId="LTAI5t8Kz6URnpkiRkJPtjGs";
        String accessSecret = "Xv7U1NfgEAD5Of6dQzSS9VuxBUjlEi";


        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", to);
        request.putQueryParameter("SignName", "老袁a洗脚6");
        request.putQueryParameter("TemplateCode", "SMS_85550034");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            return false;
        } catch (ClientException e) {
            return false;
        }
        return true;
    }
}
