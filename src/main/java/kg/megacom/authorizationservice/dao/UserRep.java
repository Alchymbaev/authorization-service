package kg.megacom.authorizationservice.dao;

import kg.megacom.authorizationservice.models.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRep extends JpaRepository<Users,Long> {
}
