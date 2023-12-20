package com.SOA.mailing.Repository;

import com.SOA.mailing.Model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<Email,Long> {
    List<Email> findByEmailTo(String emailTo);
}
