package com.example.tokoin.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class network {
    private static String postData(String data){
        String urlString = "http://192.168.1.6:8545";// geth url
        OutputStream out = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            out = new BufferedOutputStream(urlConnection.getOutputStream());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(data);
            writer.flush();
            writer.close();
            out.close();

            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                InputStream inputStream = urlConnection.getInputStream();
                byte[] bytes = new byte[0];
                bytes = new byte[inputStream.available()];
                inputStream.read(bytes);
                String res = new String(bytes);

                return res;
            }
            return "";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public static String postSolidity(String sol){
        return postData("{\"jsonrpc\":\"2.0\",\"method\":\"eth_compileSolidity\",\"params\":[" + sol + "],\"id\":1}");
    }

    public static String postAbi(String abi){
        return postData("{\"jsonrpc\":\"2.0\",\"method\":\"eth_sendRawTransaction\",\"params\":["+ abi +"],\"id\":2}");
    }

    public static String postCreate(String create){
        return postData("{\"jsonrpc\":\"2.0\",\"method\":\"eth_sendTransaction\",\"params\":["+ create +"],\"id\":3}");
    }

    public static String postModify(String modify){
        return postData("{\"jsonrpc\":\"2.0\",\"method\":\"eth_sendTransaction\",\"params\":["+ modify +"],\"id\":4}");
    }

    public static String postTransfer(String transfer){
        return postData("{\"jsonrpc\":\"2.0\",\"method\":\"eth_sendTransaction\",\"params\":["+ transfer +"],\"id\":5}");
    }

    public static String postRevocation(String revocation){
        return postData("{\"jsonrpc\":\"2.0\",\"method\":\"eth_sendTransaction\",\"params\":["+ revocation +"],\"id\":5}");
    }

    
}
