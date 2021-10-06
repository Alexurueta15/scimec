package mx.edu.utez.scimec.repository;

import mx.edu.utez.scimec.model.Appointment;
import mx.edu.utez.scimec.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment,String> {
    @Query(fields = "{student: 0}")
    List<Appointment> findAllByStudent(Student student);
    List<Appointment> findAllByDateTimeEquals(LocalDate date);
}
