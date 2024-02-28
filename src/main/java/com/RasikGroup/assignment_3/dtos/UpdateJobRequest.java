package com.RasikGroup.assignment_3.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateJobRequest extends CreateJobRequest{
    private Long job_id;
}
