package mx.edu.utez.scimec.repository;

import mx.edu.utez.scimec.model.Announcement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends MongoRepository<Announcement,String> {
}
