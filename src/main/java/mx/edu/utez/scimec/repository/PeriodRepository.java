package mx.edu.utez.scimec.repository;

import mx.edu.utez.scimec.model.Period;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodRepository extends MongoRepository<Period,String> {
}
