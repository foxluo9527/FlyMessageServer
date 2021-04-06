package com.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class SendIdentifyCode {
    private static String ENCODING = "UTF-8";

    /**
     * @param mobile 手机号码
     * @param code   验证码
     * @param model  0:注册验证;1:登录验证
     * @return 验证信息，为null则发送验证失败
     */
    public static JSONObject sendIdentifyCode(String mobile, String code, int model) {
        JSONObject result;
        String resultText;
        String postStr = Constant.SEND_URL;
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", Constant.API_KEY);
        String content = null;
        if (model == Constant.MODEL_REGISTER) {
            content = Constant.TEXT_IDENTIFY.replace("#code#", code);
        } else if (model == Constant.MODEL_LOGIN) {
            content = Constant.TEXT_LOGIN.replace("#code#", code);
        } else if (model == Constant.MODEL_CHANGE_PASS) {
            content = Constant.TEXT_CHANGE_PASS.replace("#code#", code);
        }
        params.put("text", content);
        params.put("mobile", mobile);
        resultText = post(postStr, params);
        result = (JSONObject) JSONObject.parse(resultText);
        System.out.println(resultText);
        return result;
    }

    /**
     * 基于HttpClient 4.3的通用POST方法
     *
     * @param url       提交的URL
     * @param paramsMap 提交<参数，值>Map
     * @return 提交响应
     */

    public static String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<
                        NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(),
                            param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList,
                        ENCODING));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity, ENCODING);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseText;
    }

    @SuppressWarnings({"finally", "deprecation", "resource"})
    public static String executeGet(String url) {
        BufferedReader in = null;

        String content = null;
        try {
            // 定义HttpClient  
            HttpClient client = new DefaultHttpClient();
            // 实例化HTTP方法  
            HttpGet request = new HttpGet();
            request.setURI(new URI(url));
            HttpResponse response = client.execute(request);

            in = new BufferedReader(new InputStreamReader(response.getEntity()
                    .getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            content = sb.toString();
        } finally {
            if (in != null) {
                try {
                    in.close();// 最后要关闭BufferedReader  
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return content;
        }
    }
}
