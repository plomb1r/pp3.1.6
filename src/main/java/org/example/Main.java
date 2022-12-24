package org.example;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


public class Main {
    public static final String url = "http://94.198.50.185:7081/api/users";

    public static void main(String[] args) {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(headers);
        HttpEntity<String> response = template.exchange(url, HttpMethod.GET, request, String.class);
        String cookie = response.getHeaders().getFirst(HttpHeaders.SET_COOKIE);

        System.out.println("Response " + response.toString() + "\n");
        System.out.println(cookie + "\n");
        User user = new User();
        user.setId(3L);
        user.setName("James");
        user.setLastName("Brown");
        user.setAge((byte) 23);

        create(user, headers, cookie, template);
        update(user, headers, cookie, template);
        delete(user, headers, cookie, template);

    }

    public static void create(User user, HttpHeaders headers, String cookie, RestTemplate template) {
        user.setId(3L);
        user.setName("James");
        user.setLastName("Brown");
        user.setAge((byte) 23);
        headers.add("Cookie", cookie);
        System.out.println("Cookie of create " + cookie);
        ResponseEntity<String> responseEntity = template.postForEntity(url, user, String.class);
        String createResult = responseEntity.getBody();
        System.out.println(createResult);
    }

    public static void update(User user, HttpHeaders headers, String cookie, RestTemplate template) {
        user.setId(3L);
        user.setName("Thomas");
        user.setLastName("Shelby");
        user.setAge((byte) 20);
        headers.add("Cookie", cookie);
        System.out.println("Cookie of update " + cookie);
        ResponseEntity<String> responseEntity = template.postForEntity(url, user, String.class);
        String updateResult = responseEntity.getBody();
        System.out.println(updateResult);
    }

    public static void delete(User user, HttpHeaders headers, String cookie, RestTemplate template) {
        headers.add("Cookie", cookie);
        System.out.println("Cookie of delete " + cookie);
        template.delete(url + "/3");

    }

}