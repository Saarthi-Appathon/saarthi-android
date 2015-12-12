package com.appathon.saarthi.saarthi;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * Created by shoyeb on 12-12-2015.
 */

/**
 * Usage :
 * temp = BackendConnect.makeCall("https://maps.googleapis.com/maps/api/place/search/json?location=" + latitude + "," + longitude + "&radius=500&sensor=true&type=" + place + "&key=" + GOOGLE_KEY);
 */

public class BackendConnect
{
    public static String makeCall(String url)
    {
        // string buffers the url
        StringBuffer buffer_string = new StringBuffer(url);
        String replyString = "";

        // instanciate an HttpClient
        HttpClient httpclient = new DefaultHttpClient();
        // instanciate an HttpGet
        HttpGet httpget = new HttpGet(buffer_string.toString());

        try
        {
            // get the responce of the httpclient execution of the url
            HttpResponse response = httpclient.execute(httpget);
            InputStream is = response.getEntity().getContent();

            // buffer input stream the result
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayBuffer baf = new ByteArrayBuffer(20);
            int current = 0;
            while ((current = bis.read()) != -1)
            {
                baf.append((byte) current);
            }
            // the result as a string is ready for parsing
            replyString = new String(baf.toByteArray());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(replyString);

        // trim the whitespaces
        return replyString.trim();
    }
}
