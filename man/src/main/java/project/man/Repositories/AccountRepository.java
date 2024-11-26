package project.man.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.man.models.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    Optional<Account> findOneByEmailIgnoreCase(String email);
} 

