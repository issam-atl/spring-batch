package com.issam.batch.config;

import com.issam.batch.student.Student;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;

public class StudentProcessor implements ItemProcessor<Student, Student> {

    @Override
    public @Nullable Student process(Student student) throws Exception {
        student.setId(null);
        return student;
    }
}
