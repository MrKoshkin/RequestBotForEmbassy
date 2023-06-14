package com.kosh.captcha;

import com.kosh.Application;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;

public class CaptchaManager {

    public static final Logger logger = LoggerFactory.getLogger(Application.class);

    private final String apiKey = "c8357131c9dee0802124c380f205d263";

    public String getCaptcha(String captchaId) {
        for (int i = 0; i < 10; i++) {

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }

            try {
                HttpGet httpGet = new HttpGet("http://rucaptcha.com/res.php?key=" + apiKey + "&action=get&id=" + captchaId);
                httpGet.setHeader("keep-alive", "0");

                CloseableHttpClient linksClient = HttpClients.createDefault();
                CloseableHttpResponse response = linksClient.execute(httpGet);
                try {
                    if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                        String result = EntityUtils.toString(response.getEntity());

                        if ("CAPCHA_NOT_READY".equals(result)) {
                            continue;
                        }

                        if ("ERROR_CAPTCHA_UNSOLVABLE".equals(result)) {
                            logger.error("ERROR_CAPTCHA_UNSOLVABLE, ID: " + captchaId);
                            break;
                        }

                        return result.split("\\|")[1];
                    }
                } finally {
                    httpGet.abort();
                    response.close();
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return null;
    }

    public String sendKey(String siteKey) {
        try {
            HttpGet httpGet = new HttpGet("http://rucaptcha.com/in.php?key=" + apiKey + "&method=hcaptcha&sitekey=" + siteKey + "&pageurl=https://cgifederal.secure.force.com");

            CloseableHttpClient linksClient = HttpClients.createDefault();
            CloseableHttpResponse response = linksClient.execute(httpGet);
            try {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    String result = EntityUtils.toString(response.getEntity());
                    return result.split("\\|")[1];
                }
            } finally {
                httpGet.abort();
                response.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

    public String sendCaptcha(String base64Image) {
        try {
            HttpPost httpPost = new HttpPost("http://rucaptcha.com/in.php");
            httpPost.setHeader("keep-alive", "0");

            ArrayList<NameValuePair> postParameters = new ArrayList<>();
            postParameters.add(new BasicNameValuePair("method", "base64"));
            postParameters.add(new BasicNameValuePair("key", apiKey));
            postParameters.add(new BasicNameValuePair("body", base64Image));
            postParameters.add(new BasicNameValuePair("min_len", "5"));
            postParameters.add(new BasicNameValuePair("language", "2"));
            httpPost.setEntity(new UrlEncodedFormEntity(postParameters));

            CloseableHttpClient linksClient = HttpClients.createDefault();
            CloseableHttpResponse response = linksClient.execute(httpPost);
            try {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    String result = EntityUtils.toString(response.getEntity());
                    System.out.println(result);
                    return result.split("\\|")[1];
                }
            } finally {
                httpPost.abort();
                response.close();
            }
        } catch (Throwable e) {
            logger.error("Cant send data of match", e);
        }

        return null;
    }

    public void saveCaptcha(String base64Image) {
        try {
            byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));

            File outputFile = new File("image.jpg");
            ImageIO.write(image, "jpg", outputFile);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
