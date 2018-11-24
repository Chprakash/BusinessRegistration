package chandra.prakash.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import chandra.prakash.registration.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	
	@Query("Select c from Client c where c.emailID = :emailID")
	public Client getClientByEmailID(@Param("emailID") String emailID);
}
