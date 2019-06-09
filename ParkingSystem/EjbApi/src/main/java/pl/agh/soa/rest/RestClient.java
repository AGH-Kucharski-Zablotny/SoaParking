package pl.agh.soa.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

public class RestClient {

    public static HttpRequestBase prepareRequest(String method, String url) throws UnsupportedEncodingException, JsonProcessingException {
        return prepareRequest(method, url, null);
    }

    public static HttpRequestBase prepareRequest(String method, String url, Serializable entity) throws JsonProcessingException, UnsupportedEncodingException {
        StringEntity e = new StringEntity("");
        if (entity != null) {
            e = new StringEntity(new ObjectMapper().writeValueAsString(entity));
        }

        if (HttpGet.METHOD_NAME.equals(method)) {
            return new HttpGet(url);
        }
        if (HttpPost.METHOD_NAME.equals(method)) {
            HttpPost post = new HttpPost(url);
            post.setEntity(e);
            post.setHeader("Content-type", "application/json");
            return post;
        }
        if (HttpPut.METHOD_NAME.equals(method)) {
            HttpPut put = new HttpPut(url);
            put.setEntity(e);
            put.setHeader("Content-type", "application/json");
            return put;
        }
        return null;
    }

    public static <Resp> Resp sendRequest(HttpRequestBase request, Class<Resp> resultClass) throws NotFoundException {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
                    throw new NotFoundException();
                }
            }
            if (response.getEntity() == null) {
                return null;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.getEntity().getContent(), resultClass);
        } catch (IOException e) {
            throw new InternalServerErrorException();
        }
    }
}
