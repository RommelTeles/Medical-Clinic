package com.rommel.clinical.core.customer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

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
@Table(name = "TB_CUSTOMER")
@SequenceGenerator(name = "CUST_SEQ", sequenceName = "CUST_SEQ", allocationSize = 1)
@JsonTypeName("customer")
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, use = JsonTypeInfo.Id.NAME)
public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final String COLUNA_ID = "PK_CUST";
	private static final int NAME_LENGTH = 200;
	private static final int ADRESS_LENGTH = 300;
	
	@Id
    @Column(name = COLUNA_ID)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CUST")
	private Long id;
	
	@NotNull
    @Column(name = "CUST_NAME", nullable = false)
    @Length(max = NAME_LENGTH)
	private String name;
	
	@NotNull
    @Column(name = "CUST_ADDRESS", nullable = false)
    @Length(max = ADRESS_LENGTH)
	private String address;
	

}
