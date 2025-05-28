package com.hexagonal.tasks.infrastructure.adapters;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.hexagonal.tasks.domain.models.AdditionalTaskInfo;
import com.hexagonal.tasks.domain.ports.out.ExternalServicePort;

public class ExternalServiceAdapter implements ExternalServicePort {

    // https://jsonplaceholder.typicode.com/todos/
    // https://jsonplaceholder.typicode.com/users/

    private final RestTemplate restTemplate;

    public ExternalServiceAdapter(){
        restTemplate = new RestTemplate();
    }

    @Override
    public AdditionalTaskInfo getAdditionalTaskInfo(Long taskid) {
        String apiUrl = "https://jsonplaceholder.typicode.com/todos/" + taskid;
        ResponseEntity<JsonplaceholderTodo> response = restTemplate.getForEntity(apiUrl, JsonplaceholderTodo.class);
        JsonplaceholderTodo todo = response.getBody();

        if (todo == null) {
            return null;
        }

        apiUrl = "https://jsonplaceholder.typicode.com/users/" + todo.getUserId();
        ResponseEntity<JsonplaceholderUser> userResponse =restTemplate.getForEntity(apiUrl, JsonplaceholderUser.class);
        JsonplaceholderUser user = userResponse.getBody(); 

        if (user == null) {
            return null;
        }

        return new AdditionalTaskInfo(user.getId(), user.getName(), user.getEmail());
    }

    private static class JsonplaceholderTodo {
    
        private Long id;
        private Long userId;

        /*public Jsonplaceholder(Long id, Long userId){
            this.id = id;
            this.userId = userId;
        }*/

        public Long getId(){
            return id;
        }

        public void setId(Long id){
            this.id= id;
        }

        public Long getUserId(){
            return userId;
        }

        public void setUserId(Long userId){
            this.userId = userId;
        }
    }

        private static class JsonplaceholderUser {
        
            private Long id;
            private String name;
            private String email;

         /* public JsonplaceholderUser(Long id, String name, String email){
            this.id = id;
            this.name = name;
            this.email = email;
                }*/

            public Long getId(){
                return id;
            }

            public void setId(Long id){
                this.id = id;
            }

            public String getName(){
                return name;
            }

            public void setName(String name){
                this.name = name;
            }

            public String getEmail(){
                return email;
            }

            public void setEmail(String email){
                this.email = email;
            }
            
        }
    
}
