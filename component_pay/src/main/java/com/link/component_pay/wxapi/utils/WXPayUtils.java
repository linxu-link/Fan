package com.link.component_pay.wxapi.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.link.component_pay.wxapi.constants.WXConstants;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.UUID;

public class WXPayUtils {

    private static final String TAG = "WX_TAG";

    /**
     * 生成唯一字符串
     * @return
     */
    public static String getNonceStr () {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 微信支付签名算法sign
     * @param parameters
     * @return
     */
    public static String createSign(SortedMap<String,String> parameters){
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            if(null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }

        sb.append("key=" + WXConstants.WX_MCH_KEY);
        String sign = md5Str(sb.toString()).toUpperCase();
        return sign;
    }
    /**
     * 生成32位md5码
     *
     * @param key
     * @return
     */
    public static String md5Str(String key) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = key.getBytes("UTF-8");
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 获取手机IP
     * @param context
     * @return
     */
    public static String getIpAddress(Context context){
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            // 3/4g网络
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                try {
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                //  wifi网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());
                return ipAddress;
            }  else if (info.getType() == ConnectivityManager.TYPE_ETHERNET){
                // 有限网络
                return getLocalIp();
            }
        }
        return null;
    }

    private static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }


    // 获取有限网IP
    private static String getLocalIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {

        }
        return "0.0.0.0";

    }


    public static String mapToXml (Map<String, String> map) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        for (String key : map.keySet()) {
            sb.append("<" + key + ">" + map.get(key) + "</" + key + ">");
        }
        sb.append("</xml>");
        try {
            return new String(sb.toString().getBytes("UTF-8"), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 循环解析 微信返回的 xml数据
     * @param xmlStr
     * @return
     * @throws Exception
     */
    public static JSONObject xmlToJSON (String xmlStr) throws Exception {
        //创建SAXReader对象
        SAXReader reader = new SAXReader();
        InputStream is = new ByteArrayInputStream(xmlStr.getBytes());
        //读取文件 转换成Document
        Document document = reader.read(is);
        //获取根节点元素对象
        Element node = document.getRootElement();

        return analysisNode(node, new JSONObject());
    }

    private static JSONObject analysisNode(Element node, JSONObject jsonObject) throws Exception {

        //如果当前节点内容不为空，则输出
        if(!(node.getTextTrim().equals(""))){
            jsonObject.put(node.getName(),node.getText());
        }
        //同时迭代当前节点下面的所有子节点
        //使用递归
        Iterator<Element> iterator = node.elementIterator();
        while(iterator.hasNext()){
            Element e = iterator.next();
            analysisNode(e, jsonObject);
        }
        return jsonObject;
    }

}
