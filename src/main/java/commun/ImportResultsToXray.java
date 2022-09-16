package main.java.commun;

import javax.net.ssl.SSLContext;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class ImportResultsToXray {
    public String getToken() throws IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial((chain, authType) -> true).build();
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, new String[]
                {"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}, null, NoopHostnameVerifier.INSTANCE);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();

        HttpPost request = new HttpPost("https://xray.cloud.xpand-it.com/api/v1/authenticate");

        request.addHeader("Content-Type", "application/json");

        String input = "{ \"client_id\": \""+"0281208125CC46B2B5F9B289025102D9"+"\",\"client_secret\": \""+"4d9eda59886e1f2467ac967e1fb6b92d557df8d4b370731e42626fc6c9534c51"+"\"}";
        request.setEntity(new StringEntity(input));
        String result = "";
        try (
                CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
            System.out.println(result);
            result = result.replace("\"", "");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }

    public void generateJsonResults(String status) throws IOException {

        File file = new File(System.getProperty("user.dir") + "\\data.json");

        ObjectMapper objectMapper = new ObjectMapper();

        // Create an array which will hold two JSON objects
        ObjectNode myJson = objectMapper.createObjectNode();

        // Create an array which will hold two JSON objects
        ArrayNode listTests = objectMapper.createArrayNode();

        // Creating Node that maps to JSON Object structures in JSON content
        ObjectNode info = objectMapper.createObjectNode();

        // It is similar to map put method. put method is overloaded to accept different types of data
        info.put("summary", "Execution of automated tests Syrine");
        info.put("description", "This execution is automatically");


        // Creating Node that maps to JSON Object structures in JSON content
        ObjectNode test = objectMapper.createObjectNode();

        // It is similar to map put method. put method is overloaded to accept different types of data
        test.put("testKey", "POEI22-674");
        test.put("comment", "Successful execution");
        test.put("status", status);
        listTests.add(test);

        //Sin on souhaite preciser le ID du testExec
        myJson.put("testExecutionKey","POEI22-700");
        myJson.set("info", info);
        myJson.set("tests", listTests);

        String jsonArrayAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(myJson);

        System.out.println("Created Json Array is : ");
        System.out.println(jsonArrayAsString);

        //objectMapper.writeValue(file,myJson);

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(file, myJson);


    }


    public void RemonteResultats() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException, InterruptedException {


        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial((chain, authType) -> true).build();
        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, new String[]
                {"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2"}, null, NoopHostnameVerifier.INSTANCE);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();

        HttpPost request2 = new HttpPost("https://xray.cloud.getxray.app/api/v2/import/execution");

        request2.addHeader("Content-Type", "application/json");
        request2.addHeader("Authorization", "Bearer " + getToken());
        Thread.sleep(10000);
        System.out.println("le path ="+System.getProperty("user.dir")+"\\data.json");
        StringEntity params = new StringEntity(Files.readString(Path.of(System.getProperty("user.dir")+"\\data.json"), StandardCharsets.UTF_8));
        request2.setEntity(params);

        try (
                CloseableHttpClient httpClient2 = HttpClients.createDefault();
                CloseableHttpResponse response = httpClient.execute(request2)) {
            System.out.println("Response code: " + response.getStatusLine().getStatusCode());
            HttpEntity entity2 = response.getEntity();
            Object result2 = EntityUtils.toString(entity2);
            System.out.println(result2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}