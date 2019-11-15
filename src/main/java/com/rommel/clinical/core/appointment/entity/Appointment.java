package com.rommel.clinical.core.appointment.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.rommel.clinical.core.customer.entity.Customer;
import com.rommel.clinical.core.doctor.entity.Doctor;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, of = {"id"})
@Entity
@Table(name = "TB_APPOINTMENT", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"FK_CUST", "FK_DOC"})})
@SequenceGenerator(name = "APP_SEQ", sequenceName = "APP_SEQ", allocationSize = 1)
@JsonTypeName("appointment")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class Appointment implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String COLUNA_ID = "PK_APP";

	
	@Id
    @Column(name = COLUNA_ID)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_APP")
	private Long id;
	
	@NotNull
    @ManyToOne()
    @JoinColumn(name = "FK_CUST", nullable = false)
	private Customer customer;
	
	@NotNull
    @ManyToOne()
    @JoinColumn(name = "FK_DOC", nullable = false)
	private Doctor doctor;
	
	@NotNull
	@Column(name = "APP_DATE", nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate date;

}
