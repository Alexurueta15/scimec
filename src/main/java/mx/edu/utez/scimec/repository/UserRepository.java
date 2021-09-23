package mx.edu.utez.scimec.repository;
import mx.edu.utez.scimec.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findUserById(String id);
    User findUserByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsById(String id);
}