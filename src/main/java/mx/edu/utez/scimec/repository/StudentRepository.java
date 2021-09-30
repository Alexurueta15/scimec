package mx.edu.utez.scimec.repository;

import mx.edu.utez.scimec.model.Student;
import mx.edu.utez.scimec.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student,String> {
    Student findByUser(User user);
}
