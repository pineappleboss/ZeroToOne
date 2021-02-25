package com.example.model;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @author tiankaiqiang
 * @version 1.0
 * @date 2021/2/25 17:28
 * @describe
 */
    @Document(indexName="jwang01",type="_doc")
    public class Person implements Serializable {

        @Field(name="id")
        private String id;
        @Field(name="name")
        private String name;
        @Field(name="age")
        private Integer age;
        @Field(name="email")
        private String email;
        @Field(name="hobby")
        private String hobby;
        public Person() {
            super();
            // TODO Auto-generated constructor stub
        }
        public Person(String id, String name, Integer age, String email, String hobby) {
            super();
            this.id = id;
            this.name = name;
            this.age = age;
            this.email = email;
            this.hobby = hobby;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Integer getAge() {
            return age;
        }
        public void setAge(Integer age) {
            this.age = age;
        }
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getHobby() {
            return hobby;
        }
        public void setHobby(String hobby) {
            this.hobby = hobby;
        }
        @Override
        public String toString() {
            return "Person [id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + ", hobby=" + hobby + "]";
        }

    }
