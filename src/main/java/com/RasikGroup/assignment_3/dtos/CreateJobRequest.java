package com.RasikGroup.assignment_3.dtos;


import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobRequest {
    private String title;
    private String location;
    private String salary_range;
    private Long category;
    private String requirements;
    private String description;

}
