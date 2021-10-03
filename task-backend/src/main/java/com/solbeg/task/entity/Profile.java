package com.solbeg.task.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class Profile {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID uuid;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "industry_uuid", referencedColumnName = "uuid")
    private Industry industry;

    private Boolean agreement;
}