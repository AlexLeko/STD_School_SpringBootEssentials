package br.com.devdojo.javaClient;

import br.com.devdojo.model.PageableResponse;
import br.com.devdojo.model.Student;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class JavaSpringClientTest {

    public static void main(String[] args) {

        // ======= GET ========

//        RestTemplate restTemplate = new RestTemplateBuilder()
//                .rootUri("http://localhost:8080/v1/protected/students")
//                .basicAuthentication("toyo", "devdojo")
//                .build();

        // OBJECT
//        Student student = restTemplate.getForObject("/{id}", Student.class, 3);
//        System.out.println(student);

        // ENTITY
//        ResponseEntity<Student> forEntity = restTemplate.getForEntity("/{id}", Student.class, 3);
//        System.out.println(forEntity.getBody());

//        // ARRAY
//        Student[] students = restTemplate.getForObject("/", Student[].class);
//        System.out.println(Arrays.toString(students));
//
//        // LIST
//        ResponseEntity<List<Student>> exchange = restTemplate.exchange("/", HttpMethod.GET, null,
//                new ParameterizedTypeReference<List<Student>>() {});
//        System.out.println(exchange.getBody());

        // PAGEABLE
//        ResponseEntity<PageableResponse<Student>> exchangePage = restTemplate.exchange("/", HttpMethod.GET, null,
//                new ParameterizedTypeReference<PageableResponse<Student>>() {});
//        System.out.println(exchangePage.getBody().getContent());

        // SORT
//        ResponseEntity<PageableResponse<Student>> exchangePage = restTemplate.exchange("/?sort=id,desc&sort=name,asc", HttpMethod.GET, null,
//                new ParameterizedTypeReference<PageableResponse<Student>>() {});
//        System.out.println(exchangePage.getBody().getContent());


        // ======= POST ========
        RestTemplate restTemplateAdmin = new RestTemplateBuilder()
                .rootUri("http://localhost:8080/v1/admin/students")
                .basicAuthentication("toyo", "devdojo")
                .build();

        Student studentPost = new Student();
        studentPost.setName("John Travolta");
        studentPost.setEmail("johnTra@aviator.com");
        studentPost.setId(5L);

//        ResponseEntity<Student> exchangePost = restTemplateAdmin.exchange("/",
//                HttpMethod.POST, new HttpEntity<>(studentPost, createJSONHeader()), Student.class);
//        System.out.println(exchangePost);
//
//        Student studentPostForObject = restTemplateAdmin.postForObject("/", studentPost, Student.class);
//        System.out.println(studentPostForObject);
//
//        ResponseEntity<Student> studentPostForEntity = restTemplateAdmin.postForEntity("/", studentPost, Student.class);
//        System.out.println(studentPostForEntity);


        JavaClientDAO dao = new JavaClientDAO();

        System.out.println(dao.findById(52));
//        System.out.println(dao.listAll());
//        System.out.println(dao.save(studentPost));
//        dao.update(studentPost);
//        dao.delete(52);

    }

    private static HttpHeaders createJSONHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }


}
