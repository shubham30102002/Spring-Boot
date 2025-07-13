package com.example.hibernateMapping.dao;

import com.example.hibernateMapping.entity.Course;
import com.example.hibernateMapping.entity.Instructor;
import com.example.hibernateMapping.entity.InstructorDetail;
import com.example.hibernateMapping.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public class AppDaoImpl implements AppDao{

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructor = entityManager.find(Instructor.class, id);
        //remove associate courses from instructor
        List<Course> courses = instructor.getCourses();
        for(Course course: courses){
            course.setInstructor(null);
        }
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);
        //remove associate object reference and break bi directional
        instructorDetail.getInstructor().setInstructorDetail(null);
        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCourseByInstructorId(int id) {
        TypedQuery<Course> typedQuery = entityManager.createQuery(
                "from Course where instructor.id = :data ", Course.class);
        //same name for table as of class name in query
        typedQuery.setParameter("data", id);
        return typedQuery.getResultList();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i "
                +"JOIN FETCH i.courses "
                +"where i.id= :data" , Instructor.class
        );

        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course course = entityManager.find(Course.class, id);
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {
        //create query
        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c FROM Course c JOIN FETCH c.reviews where c.id= :data", Course.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    public Course findCourseAndStudentByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c FROM Course c JOIN FETCH c.students where c.id= :data", Course.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int id) {
        TypedQuery<Student> query = entityManager.createQuery(
                "SELECT s FROM Student s JOIN FETCH s.courses where s.id= :data", Student.class);
        query.setParameter("data", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {
        Student student = entityManager.find(Student.class, id);
        if(student == null){
            System.out.println("No student for found for the id: "+ id);
            return;
        }
        List<Course> courses = student.getCourses();
        //break the association for the course and student
        for(Course course: courses){
            course.getStudents().remove(student);
        }
        //delete student
        entityManager.remove(student);
    }
}
