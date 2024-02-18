package com.RasikGroup.assignment_3.entity.entities;

import com.RasikGroup.assignment_3.dtos.CreateJobRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String location;
    private String salary_range;
    private String requirements;

    @ManyToOne
    private CategoryEntity category;

    private String Description;

    @Override
    public String toString(){
        return String.format("ID: %d, Title: %s", this.id, this.title);
    }


}
