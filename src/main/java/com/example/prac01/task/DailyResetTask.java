package com.example.prac01.task;

import com.example.prac01.repository.DailyCountRepository;
import com.example.prac01.singleton.DailyCount;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DailyResetTask {

    private final DailyCountRepository dailyCountRepository;

    private DailyCount dailyCount;


    @PostConstruct
    public void init(){
        dailyCount = dailyCountRepository.findAll().stream().findFirst().orElseGet(()->{
            DailyCount new_daily = new DailyCount();
            return dailyCountRepository.save(new_daily);
        });
    }

    public int getDailyCount(){
        return dailyCount.getDailyCount();
    }

    public void waste(){
        dailyCount.waste();
        dailyCountRepository.save(dailyCount);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    private void resetDailyCount(){
        dailyCount.reset();
        dailyCountRepository.save(dailyCount);
        log.info("Daily G-SMTP Count has been reset to 500");
    }
}
