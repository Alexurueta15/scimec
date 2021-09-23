package mx.edu.utez.scimec.repository;

import mx.edu.utez.scimec.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment,String> {
}
