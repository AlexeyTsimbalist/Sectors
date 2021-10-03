package com.solbeg.task.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class Industry {
    @Id
    private UUID uuid;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_uuid")
    private Industry parent;

    @ToString.Exclude
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Industry> children;
}