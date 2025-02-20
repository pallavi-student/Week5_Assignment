package com.day2json;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;



    class Employee {
        private String name;
        private int age;

        public Employee(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return name; }
        public int getAge() { return age; }
    }

    public class ListToJson {
        public static void main(String[] args) throws Exception {
            ObjectMapper objectMapper = new ObjectMapper();

            List<Employee> employees = Arrays.asList(
                    new Employee("Ninja Hattori", 30),
                    new Employee("Bheem", 25)
            );

            String jsonArray = objectMapper.writeValueAsString(employees);
            System.out.println(jsonArray);
        }
    }

