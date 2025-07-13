package com.example.validateDemo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.validation.beanvalidation.CustomValidatorBean;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

   private String coursePrefix;

    @Override
    public void initialize(CourseCode theCourseCode) {
       coursePrefix = theCourseCode.value();
    }

    @Override
    public boolean isValid(String theCode, ConstraintValidatorContext constraintValidatorContext) {
        if(coursePrefix!=null) {
            return theCode.startsWith(coursePrefix);
        }
        return true;
    }
}
