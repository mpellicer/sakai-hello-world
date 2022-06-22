package com.edf.helloworld.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EDF_ANOTACION")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Anotacion {

    @Id
    @Column(name = "ID_ANOTACION", length = 36, nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "ANOTACION")
    private String anotacion;

    @Column(name = "PERSONA", length = 99, nullable = false)
    private String persona;

    @Column(name = "visible", nullable = false)
    private boolean visible;

}

