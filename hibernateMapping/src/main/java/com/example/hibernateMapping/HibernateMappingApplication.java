package com.example.hibernateMapping;

import com.example.hibernateMapping.dao.AppDao;
import com.example.hibernateMapping.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class HibernateMappingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateMappingApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(AppDao appDao){
		return  runner -> {
			//createInstructor(appDao);

			//findInstructor(appDao);

			//deleteInstructor(appDao);

			//findInstructorDetail(appDao);

			//deleteInstructorDetail(appDao);

			//createInstructorWithCourses(appDao);

			//findInstructorWithCourses(appDao);

			//findCourseForInstructor(appDao);
			
			//findInstructorByCoursesJoinFetch(appDao);

			//updateInstructor(appDao);
			
			//updateCourse(appDao);

			//deleteCourse(appDao);

			//createCourseAndReviews(appDao);

			//findCourseAndReview(appDao);

			//deleteCourseAndReviews(appDao);

			//createCourseAndStudents(appDao);

			//findCourseAndStudent(appDao);

			//findStudentAndCourses(appDao);

			//addMoreCourseForStudents(appDao);

			deleteStudent(appDao);
		};
	}

	private void deleteStudent(AppDao appDao) {
		int id= 2;
		System.out.println("Deleting the student having id: "+ id);
		appDao.deleteStudentById(id);
		System.out.println("Deleted!!");
	}

	private void addMoreCourseForStudents(AppDao appDao) {
		int id = 2;
		Student student = appDao.findStudentAndCoursesByStudentId(id);
		Course course = new Course("Chess", 20);
		Course course2 = new Course("System Design", 80);
		Course course3 = new Course("Cloud development", 50);

		student.addCourse(course);
		student.addCourse(course2);
		student.addCourse(course3);

		System.out.println("Student updated courses list: "+ student.getCourses());
		appDao.update(student);
		System.out.println("Saved the courses!!");

	}

	private void findStudentAndCourses(AppDao appDao) {
		int id = 2;
		Student student = appDao.findStudentAndCoursesByStudentId(id);
		System.out.println("Student found: "+ student);
		System.out.println("Course acquired by student: "+ student.getCourses());
	}

	private void findCourseAndStudent(AppDao appDao) {
		int id = 5;
		Course course = appDao.findCourseAndStudentByCourseId(id);
		System.out.println("Course found: "+ course);
		System.out.println("Associated student: "+ course.getStudents());
	}

	private void createCourseAndStudents(AppDao appDao) {
		//create course
		Course course = new Course("Literature", 100);

		//add students
		Student student1 = new Student("John", "Doe", "johndoe@gmail.com");
		Student student2 = new Student("Shubham","Gupta", "shubhamrg30@gmail.com");

		//add student to the course
		course.addStudent(student1);
		course.addStudent(student2);

		//save the course
		System.out.println("saving the course: "+ course);
		System.out.println("saving the students: "+ course.getStudents());
		appDao.save(course);
		System.out.println("Course saved!!");
	}

	private void deleteCourseAndReviews(AppDao appDao) {
		int id = 4;
		System.out.println("Deleting course with id: "+ id);
		appDao.deleteCourseById(id);
	}

	private void findCourseAndReview(AppDao appDao) {
		int id = 4;
		Course course = appDao.findCourseAndReviewsByCourseId(id);
		System.out.println("Course found: "+ course);
		System.out.println("Reviews for the course: " + course.getReviews());
	}

	private void createCourseAndReviews(AppDao appDao) {
		//create course
		Course course = new Course("Core Java", 20);
		//add reviews
		course.addReview(new Review("Great course... Loved it"));
		course.addReview(new Review("Best course... Worth watching"));
		course.addReview(new Review("Bad course... waste of money"));

		System.out.println("saving the course: "+ course);
		System.out.println("And reviews : "+ course.getReviews());

		//save course and reviews
		appDao.save(course);
	}


	private void deleteCourse(AppDao appDao) {
		int id = 6;
		System.out.println("Deleting course having id: "+ id);
		appDao.deleteCourseById(id);
		System.out.println("Deleted!!!");
	}

	private void updateCourse(AppDao appDao) {
		int instructor_id = 4;
		Instructor instructor = appDao.findInstructorByIdJoinFetch(instructor_id);
		if(instructor == null){
			System.out.println("No instructor found with id: "+ instructor_id);
			return;
		}
		System.out.println("Courses found: "+ instructor.getCourses());
		Course course = instructor.getCourses().get(0);
		course.setTitle("Complete web development");
		appDao.update(course);
		System.out.println("Updated course: "+ course);
	}

	private void updateInstructor(AppDao appDao) {
		int instructor_id = 4;
		Instructor instructor = appDao.findInstructorById(instructor_id);
		if(instructor == null){
			System.out.println("No instructor found with id: "+ instructor_id);
			return;
		}
		System.out.println("Instructor found: " + instructor);
		instructor.setLastName("Love");
		appDao.update(instructor);
		System.out.println("Updated instructor: "+ instructor);
	}

	private void findInstructorByCoursesJoinFetch(AppDao appDao) {
		int instructor_id = 4;
		Instructor instructor = appDao.findInstructorByIdJoinFetch(instructor_id);
		if(instructor == null){
			System.out.println("No instructor found with id: "+ instructor_id);
			return;
		}
		System.out.println("Instructor through fetch query: "+ instructor);
		System.out.println("Associated courses: "+ instructor.getCourses());
		System.out.println("Success!!!");
	}

	private void findCourseForInstructor(AppDao appDao) {
		int instructor_id = 4;
		Instructor instructor = appDao.findInstructorById(instructor_id);
		if(instructor == null){
			System.out.println("No instructor found with id: "+ instructor_id);
			return;
		}
		System.out.println("Instructor: "+ instructor);

		List<Course> courses = appDao.findCourseByInstructorId(instructor_id);
		//add courses in instructor object
		instructor.setCourses(courses);
		System.out.println("Course are: "+ instructor.getCourses());
	}

	private void findInstructorWithCourses(AppDao appDao) {
		int instructor_id=4;
		Instructor instructor = appDao.findInstructorById(instructor_id);
		if(instructor == null){
			System.out.println("No instructor found with id: "+ instructor_id);
			return;
		}
		System.out.println("Instructor" +instructor);
		System.out.println("the associated courses: "+ instructor.getCourses());

	}

	private void createInstructorWithCourses(AppDao appDao) {
		Instructor instructor = new Instructor("Love", "Babbar", "lovebabbar@gmail.com");

		InstructorDetail instructorDetail = new InstructorDetail("http://wwww.lovebabbar.com", "C++ dsa");

		//assosiate the objects
		instructor.setInstructorDetail(instructorDetail);

		Course tempCourse = new Course("DSA in C++", 56);
		Course tempCourse2 = new Course("Web Development", 100);

		instructor.add(tempCourse);
		instructor.add(tempCourse2);

		System.out.println("Saving the instrutor: "+ instructor);
		System.out.println("Saving with courses: "+ instructor.getCourses());
		appDao.save(instructor);
		System.out.println("Done");
	}

	private void deleteInstructorDetail(AppDao appDao) {
		int instructor_id = 3;
		System.out.println("Deleting the instructor with id: "+ instructor_id);
		appDao.deleteInstructorDetailById(instructor_id);
		System.out.println("Deleted!!");
	}

	private void findInstructorDetail(AppDao appDao) {
		int instructor_id = 2	;
		InstructorDetail tempInstructorDetail = appDao.findInstructorDetailById(instructor_id);

		// print the instructor detail
		System.out.println("tempInstructorDetail: " + tempInstructorDetail);

		// print the associated instructor
		System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

		System.out.println("Done!");
	}

	private void deleteInstructor(AppDao appDao) {
		int instructor_id = 4;
		System.out.println("Deleting the instructor with id: "+ instructor_id);
		appDao.deleteInstructorById(instructor_id);
		System.out.println("Deleted!!");
	}

	private void findInstructor(AppDao appDao) {
		int instructor_id = 1;
		//find instructor
		Instructor instructor = appDao.findInstructorById(instructor_id);

		//check if it exist
		if(instructor == null){
			System.out.println("Instructor does not exist for id: "+ instructor_id);
		}
		System.out.println("Instructor found with id- " +instructor_id+" is: "+instructor.toString());
	}

	private void createInstructor(AppDao appDao) {
		Instructor instructor = new Instructor("Shraddha", "Kapoor", "shraddha@gmail.com");

		InstructorDetail instructorDetail = new InstructorDetail("http://wwww.apnaCollege.com/youtube", "Java dsa");

		//assosiate the objects
		instructor.setInstructorDetail(instructorDetail);

		System.out.println("Saving the instrutor: "+ instructor);
		appDao.save(instructor);
		System.out.println("Done");
	}
}
