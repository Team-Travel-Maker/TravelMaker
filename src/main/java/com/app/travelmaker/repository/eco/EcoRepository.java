package com.app.travelmaker.repository.eco;

import com.app.travelmaker.entity.eco.Eco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EcoRepository extends JpaRepository<Eco, Long>, EcoDSL {
}
