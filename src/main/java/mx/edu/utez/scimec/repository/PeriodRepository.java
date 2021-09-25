package mx.edu.utez.scimec.repository;

import mx.edu.utez.scimec.model.Period;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodRepository extends MongoRepository<Period,String> {
    Period findFirstByEnabledTrue();
}
