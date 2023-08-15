package com.app.travelmaker.repository.cs;

import com.app.travelmaker.entity.cs.CsAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CsAnswerRepository extends JpaRepository<CsAnswer, Long>, CsAnswerDSL {
}
