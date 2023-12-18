package net.fernandosalas.backend.repository;

import net.fernandosalas.backend.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<Register, Long> {
}
