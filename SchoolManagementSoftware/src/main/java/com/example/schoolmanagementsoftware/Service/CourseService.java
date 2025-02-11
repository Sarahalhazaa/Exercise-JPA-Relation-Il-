package com.example.schoolmanagementsoftware.Service;

import com.example.schoolmanagementsoftware.Api.ApiException;
import com.example.schoolmanagementsoftware.Model.Course;
import com.example.schoolmanagementsoftware.Model.Teacher;
import com.example.schoolmanagementsoftware.Repository.CourseRepository;
import com.example.schoolmanagementsoftware.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    public List<Course> getCourse() {
        return courseRepository.findAll();
    }

    public void addCourse( Integer teacherId, Course course) {
        Teacher teacher1 = teacherRepository.findTeacherById(teacherId);
        if (teacher1 == null) {
            throw new ApiException("id not found");
        }
        course.setTeacher(teacher1);
       courseRepository.save(course);
    }
    public void updateCourse(Integer id, Course course) {
        Course course1 = courseRepository.findCourseById(id);
        if (course1 == null) {
            throw new ApiException("id not found");
        }
      course1.setName(course.getName());
        courseRepository.save(course1);

    }

    public void deleteCourse(Integer id) {
        Course course1 = courseRepository.findCourseById(id);
        if (course1 == null) {
            throw new ApiException("id not found");
        }
       courseRepository.delete(course1);
    }

    //---------------------------  end CRUD  ---------------------------------
    public String getTeacherName(Integer id) {
        Course course1 = courseRepository.findCourseById(id);
        if (course1 == null) {
            throw new ApiException("id not found");
        }

       return course1.getTeacher().getName();
    }
}
