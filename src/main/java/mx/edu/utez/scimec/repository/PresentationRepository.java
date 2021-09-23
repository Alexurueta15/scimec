package mx.edu.utez.scimec.repository;

import mx.edu.utez.scimec.model.Presentation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentationRepository extends MongoRepository<Presentation,String> {
}
