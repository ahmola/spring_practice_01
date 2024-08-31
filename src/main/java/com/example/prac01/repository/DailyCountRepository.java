package com.example.prac01.repository;

import com.example.prac01.singleton.DailyCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyCountRepository extends JpaRepository<DailyCount, Long> {
}
