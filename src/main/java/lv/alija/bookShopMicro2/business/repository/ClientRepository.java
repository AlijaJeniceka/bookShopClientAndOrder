package lv.alija.bookShopMicro2.business.repository;

import lv.alija.bookShopMicro2.business.repository.model.ClientDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientDAO, Long> {
}
