package io.upschool.repository;

import io.upschool.entities.FlightCrewMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightCrewMemberRepository extends JpaRepository<FlightCrewMember,Long> {
}
