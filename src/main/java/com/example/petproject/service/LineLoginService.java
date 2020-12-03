package com.example.petproject.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.petproject.bean.LineUser;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.HttpURLConnection;

@Service
public class LineLoginService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private static final String TOEKN_URL = "https://api.line.me/oauth2/v2.1/token";
    private static final String AUTH_URL = "https://my-zoo.herokuapp.com/gotoauthpage";
    private static final String GRANT_TYPE = "authorization_code";

    @Value("${line.clientId}")
    private String clientId;
    @Value("${line.clientSecret}")
    private String clientSecret;
    @Value("${line.redirectUri}")
    private String redirectUri;

    @Autowired
    private RestTemplate restTemplate;

    public LineUser getLineUserInfo(String code) {
        String idToken = getAccessToken(code);
        return decodeJWT(idToken);
    }

    public String lineAuth() {
        RestTemplate restTemplate = new RestTemplate( new SimpleClientHttpRequestFactory(){
            @Override
            protected void prepareConnection(HttpURLConnection connection, String httpMethod ) {
                connection.setInstanceFollowRedirects(false);
            }
        } );

        ResponseEntity<Object> response = restTemplate.exchange(AUTH_URL, HttpMethod.GET, null, Object.class);
        String location = response.getHeaders().getLocation() == null ? "" : response.getHeaders().getLocation().toString();
        return location.replaceFirst("scope=", "scope=email%20");
    }

    private String getAccessToken(String code) {
        MultiValueMap<String, String> requestMap= new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        requestMap.add("code", code);
        requestMap.add("grant_type", GRANT_TYPE);
        requestMap.add("redirect_uri", redirectUri);
        requestMap.add("client_id", clientId);
        requestMap.add("client_secret", clientSecret);
        String idToken = "";
        try {
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestMap, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(TOEKN_URL, requestEntity , String.class);
            JSONObject jsonObject = new JSONObject(response.getBody());
            idToken = jsonObject.getString("id_token");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return idToken;
    }

    private LineUser decodeJWT(String idToken) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(clientSecret)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(idToken);
        String name = decodedJWT.getClaim("name").asString();
        String picture = decodedJWT.getClaim("picture").asString();
        String email = decodedJWT.getClaim("email").asString();
        return new LineUser(name, picture, email);
    }

    public static void main(String[] args) {
        String url = "https://access.line.me/oauth2/v2.1/authorize?response_type=code&client_id=1655289259&redirect_uri=http%3A%2F%2F192.168.0.49%3A8080%2Fmyzoo%2Fline-login&state=Z2l1Jy5g-7pGYpd3ElYiHu_aWDv_IBzuxqhEG2MGH5U&scope=openid%20profile&nonce=XWq-01PTctUwf9JCNueFRNxyexVVWB_ZUB37Mfz18Mg";

        System.out.println("url = " + url);
    }

}
