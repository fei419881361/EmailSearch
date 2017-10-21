package com.query.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by exphuhong on 17-9-13.
 * Start
 */
public class CrawlerUtils {
    static HostnameVerifier hv = new HostnameVerifier() {
        public boolean verify(String urlHostName, SSLSession session) {
            System.out.println("Warning: URL Host: " + urlHostName + " vs. "
                    + session.getPeerHost());
            return true;
        }
    };

    public static int Net(String email) {
        {
            try {
            trustAllHttpsCertificates();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
        }
        try {
            String url_reg007 = "https://www.reg007.com/search?q=";
            StringBuilder reg_result = buildConnection(url_reg007 +email);
            Document reg_document = Jsoup.parse(reg_result.toString());
            Element reg_element = reg_document.getElementById("resultCount");

            String url_baidu = "https://www.baidu.com/s?wd=\"email\"";
            Document doc = Jsoup.connect(url_baidu.replace("email",email)).get();
            String result = doc.getElementsByClass("nums").get(0).text();
            String res = "[^0-9]";
            Pattern p = Pattern.compile(res);
            Matcher m = p.matcher(result);

            int baidu_result = Integer.parseInt(m.replaceAll("").trim());
            //int reg007_result = Integer.parseInt(reg_element.html());
            return baidu_result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private static void trustAllHttpsCertificates() throws Exception {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
                .getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc
                .getSocketFactory());
    }
    static class miTM implements javax.net.ssl.TrustManager,
            javax.net.ssl.X509TrustManager {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

        public void checkClientTrusted(
                java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }
    }

    private static StringBuilder buildConnection(String path) throws IOException {
        URL url;
        HttpURLConnection connection;
        url = new URL(path);

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }
        return result;
    }

}