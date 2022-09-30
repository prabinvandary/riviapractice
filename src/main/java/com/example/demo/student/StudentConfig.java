//package com.example.demo.student;
//
//import com.example.demo.model.Student;
//import com.example.demo.repository.StudentRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.lang.reflect.Array;
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//public class StudentConfig {
//    @Bean
//    CommandLineRunner commandLineRunner(StudentRepository repository){
//        StudentRepository repository1;
//        return  args->{
//             Student prabin=new Student(
//                        "Prbin Bhandari",
//                        "prabinbhandari2056@gmail.com",
//                        LocalDate.of(1999, Month.SEPTEMBER, 26)
//
//                );
//            Student pranish=new Student(
//                    "Pranish Adhikari",
//                    "pranishadhikari@gmail.com",
//                    LocalDate.of(2015, Month.SEPTEMBER, 17)
//            );
//            Student dipti=new Student(
//                    "Dipti Ghimire",
//                    "diptighimire@gmail.com",
//                    LocalDate.of(2016, Month.MARCH, 26)
//            );
//            Student divyansha=new Student(
//                    "Divyansha Ghimire",
//                    "divyanshaghimire@gmail.com",
//                    LocalDate.of(2020, Month.SEPTEMBER, 26)
//            );
//            ArrayList<Student> arrayList=new ArrayList<Student>();
//            arrayList.add(prabin);
//            arrayList.add(pranish);
//            arrayList.add(dipti);
//            arrayList.add(divyansha);
//            repository.saveAll(arrayList);
//        };
//    }
//}
