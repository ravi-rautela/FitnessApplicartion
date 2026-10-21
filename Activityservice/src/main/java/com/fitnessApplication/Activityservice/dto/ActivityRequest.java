package com.fitnessApplication.Activityservice.dto;

import com.fitnessApplication.Activityservice.model.ActivityType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Getter
@Setter
public class ActivityRequest {

     private String userId;
    private ActivityType type;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private Map<String, Object> additionalMetrics;

}
