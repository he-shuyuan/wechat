package com.ower.hsy.common.core.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

public abstract class HttpInvokerUtil {

    private static final Logger logger = Logger.getLogger(HttpInvokerUtil.class);


    public static String post(String httpUrl, String requestData, String contentType) throws Exception {
        return post(httpUrl, requestData, "UTF-8", contentType);
    }

    public static String post(String httpUrl, String requestData, String encoding, String contentType) throws Exception {
        URL url = null;
        try {
            url = new URL(httpUrl);
        } catch (MalformedURLException e) {
            logger.error(e);
            throw new Exception("访问地址错误！", e);
        }
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", contentType + "; charset=" + encoding);
        conn.setRequestProperty("Accept-Charset", encoding);
        conn.setRequestProperty("Accept-Language", "zh-cn");
        OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), encoding);
        if (requestData != null) {
            try {
                out.write(requestData);
                out.flush();
            } finally {
                out.close();
            }
        }
        String result = "";

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                result = result + line;
            }
        } finally {
            reader.close();
        }
        return result;
    }

    public static String get(String url, Map<String, String> param) {
        String result = "";
        BufferedReader in = null;

        StringBuffer buferUrl = new StringBuffer(url);

        logger.debug("封装参数");
        int size = param.entrySet().size();
        int index = 0;
        for (Map.Entry<String, String> entry : param.entrySet()) {
            index++;
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            logger.debug(key + "=" + value);
            if (index == 1) {
                buferUrl.append("?");
            }
            buferUrl.append(key).append("=").append(value);
            if (index != size) {
                buferUrl.append("&");
            }
        }
        String requestUrl = buferUrl.toString();
        logger.debug("request url =[" + requestUrl + "]");
        try {
            URL realUrl = new URL(requestUrl);

            URLConnection connection = realUrl.openConnection();

            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            connection.connect();

            Map<String, List<String>> map = connection.getHeaderFields();
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result = result + line;
            }
           
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            
        }
        return result;
    }

    public static String get(String url) {
        String result = "";
        BufferedReader in = null;
        String urlNameString = url;
        try {
            URL realUrl = new URL(urlNameString);

            URLConnection connection = realUrl.openConnection();

            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            connection.connect();

            Map<String, List<String>> map = connection.getHeaderFields();
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result = result + line;
            }
           
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static void download(String httpUrl, HttpServletResponse response) {
        try {
            URL url = null;
            try {
                url = new URL(httpUrl);
            } catch (MalformedURLException e) {
                logger.error(e);
                throw new Exception("访问地址错误！", e);
            }
            URLConnection conn = url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
            System.setProperty("sun.net.client.defaultReadTimeout", "30000");
            if (httpUrl.startsWith("https")) {
                conn.connect();
            }
            response.setHeader("Content-Type", conn.getContentType());
            response.setHeader("Content-Disposition", conn.getHeaderField("Content-Disposition"));
            response.setHeader("Content-Length", String.valueOf(conn.getContentLength()));

            InputStream inputStream = conn.getInputStream();
            OutputStream outputStream = null;
            try {
                outputStream = response.getOutputStream();
                int len = 0;
                byte[] buf = new byte[1024];
                while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                    outputStream.write(buf, 0, len);
                }
                outputStream.flush();
            } catch (IOException localIOException) {} finally {
                if (outputStream != null) {
                    IOUtils.closeQuietly(outputStream);
                }
                if (inputStream != null) {
                    IOUtils.closeQuietly(inputStream);
                }
            }
        } catch (Exception e) {
            logger.error(e);
            logger.info("httpUrl=" + httpUrl);
        }
    }

    public static String upload(String httpUrl, InputStream is, String fileName) {
        try {
            URL url = null;
            try {
                url = new URL(httpUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw new Exception("访问地址错误！", e);
            }
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);

            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");

            String BOUNDARY = "----------" + System.currentTimeMillis();
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            con.setRequestProperty("Content-Length", String.valueOf(is.available()));

            StringBuilder sb = new StringBuilder();
            sb.append("--");
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + fileName + "\"\r\n");
            sb.append("Content-type：multipart/form-data\r\n\r\n");

            byte[] head = sb.toString().getBytes("utf-8");

            OutputStream out = new DataOutputStream(con.getOutputStream());
            try {
                out.write(head);

                DataInputStream in = new DataInputStream(is);
                try {
                    int bytes = 0;
                    byte[] bufferOut = new byte[1024];
                    while ((bytes = in.read(bufferOut)) != -1) {
                        out.write(bufferOut, 0, bytes);
                    }
                } finally {}
                byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");

                out.write(foot);

                out.flush();
            } finally {
                out.close();
            }
            String result = "";

            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    result = result + line;
                }
            } finally {
                reader.close();
            }
            return result;
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }
}
