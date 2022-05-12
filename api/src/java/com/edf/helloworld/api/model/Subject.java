package com.edf.helloworld.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EDF_SUBJECT", indexes = {@Index(name = "IDX_EDF_SUBJECT_CONTEXT", columnList = "CONTEXT")})
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "context")
public class Subject {

    @Id
    @Column(name = "CONTEXT", length = 99, nullable = false)
    private String context;

    @Column(name = "TITLE", length = 99, nullable = false)
    private String title;

}

