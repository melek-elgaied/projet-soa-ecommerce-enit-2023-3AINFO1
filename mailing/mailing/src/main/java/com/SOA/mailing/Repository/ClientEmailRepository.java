package com.SOA.mailing.Repository;

import com.SOA.mailing.Model.ClientEmail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientEmailRepository extends JpaRepository<ClientEmail, Long> {
}
