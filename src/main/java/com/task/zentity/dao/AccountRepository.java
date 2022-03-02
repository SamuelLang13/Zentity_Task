package com.task.zentity.dao;

import com.task.zentity.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    public Account findByIBAN(String IBAN);
    public boolean existsByIBAN(String IBAN);
}
