package ssau.kuznetsov.ddsss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssau.kuznetsov.protobuf.DdsssModels;

@RestController
public class CourseController {

    @Autowired
    CourseRepository courseRepo;

    @RequestMapping("/courses/{id}")
    DdsssModels.Course customer(@PathVariable Integer id) {
        return courseRepo.getCourse(id);
    }
}