package ssau.kuznetsov.ddsss;

import ssau.kuznetsov.protobuf.DdsssModels;

import java.util.Map;

public class CourseRepository {

    Map<Integer, DdsssModels.Course> courses;

    public CourseRepository(Map<Integer, DdsssModels.Course> courses) {
        this.courses = courses;
    }

    public DdsssModels.Course getCourse(int id) {
        return courses.get(id);
    }
}
