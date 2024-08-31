package com.example.prac01.singleton;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class DailyCount { //singleton

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private Integer dailyCount = 500;
    public void waste(){
       this.dailyCount -= 1;
    }

    public void reset(){
        this.dailyCount = 500;
    }
}
