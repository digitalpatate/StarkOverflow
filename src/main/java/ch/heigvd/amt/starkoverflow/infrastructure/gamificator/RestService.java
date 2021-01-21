package ch.heigvd.amt.starkoverflow.infrastructure.gamificator;

import ch.heigvd.amt.starkoverflow.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Named("RestService")
@ApplicationScoped
@AllArgsConstructor
@Log
@NoArgsConstructor
public class RestService {

    private String key = "b911018e-da71-4000-82b5-388e4b8fb5c5";

    private String secret = "YnZdXMdUkP";

    private String baseUrl = "http://localhost:8080";

    public Object get(String path, Class<?> type) throws NotFoundException {
        RestTemplate restTemplate = new RestTemplate();

        String url = baseUrl + path;
        HttpEntity<String> req = new HttpEntity<>(constructHeader(createSignature(url)));

        try {
            ResponseEntity<?> res =  restTemplate.exchange(url,HttpMethod.GET,req,type);
            System.out.println(res.getBody());
            return res.getBody();
        }catch (RestClientException e){
            System.out.println("Got an error in RestService"+ e.getMessage());
            throw new NotFoundException(e.getMessage());
        }
    }

    private String createSignature(String url){
        //Remove querystring
        url = url.split("\\?")[0];
        String data = String.format("%s%s",key,url);

        String hmac = new HmacUtils(HmacAlgorithms.HMAC_SHA_1, secret).hmacHex(data);
        String signature = Base64.getEncoder().encodeToString(hmac.getBytes(StandardCharsets.UTF_8));

        return signature;
    }

    private HttpHeaders constructHeader(String signature){
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-api-key",key);
        headers.add("signature",signature);

        return headers;
    }

    public void post(String path, Object body) {

        RestTemplate restTemplate = new RestTemplate();
        System.out.println(path);

        String url = baseUrl + path;
        HttpEntity req = new HttpEntity<>(body,constructHeader(createSignature(url)));
        restTemplate.exchange(url,HttpMethod.POST, req, Object.class);

    }
}
