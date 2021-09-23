package mx.edu.utez.scimec.repository;

import mx.edu.utez.scimec.model.Worker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends MongoRepository<Worker,String> {
}
