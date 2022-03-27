package ssau.kuznetsov.ddsss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import ssau.kuznetsov.protobuf.DdsssModels;

import java.util.*;

@SpringBootApplication
public class DdsssApp {

    public static void main(String[] args) {
        SpringApplication.run(DdsssApp.class, args);
    }

    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }

    @Bean
    RestTemplate restTemplate(ProtobufHttpMessageConverter hmc) {
        return new RestTemplate(List.of(hmc));
    }

    @Bean
    public CourseRepository createTestCourses() {
        Map<Integer, DdsssModels.Course> courses = new HashMap<>();

        DdsssModels.Course course1 = DdsssModels.Course.newBuilder()
                .setId(1)
                .setCourseName("REST with Spring")
                .addAllStudent(new ArrayList<>())
                .build();

        DdsssModels.Course course2 = DdsssModels.Course.newBuilder()
                .setId(2)
                .setCourseName("Learn Spring Security")
                .addAllStudent(new ArrayList<>())
                .build();

        courses.put(course1.getId(), course1);
        courses.put(course2.getId(), course2);

        return new CourseRepository(courses);
    }
}
