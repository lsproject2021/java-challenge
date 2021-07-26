package jp.co.axa.apidemo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="EMPLOYEE")
@JsonInclude(Include.NON_NULL)
public class Employee {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonProperty("id")
    // min and max range values
    @Min(0)
    @Max(Long.MAX_VALUE)
    private Long id;

    @Getter
    @Setter
    @Column(name="EMPLOYEE_NAME")
    @JsonProperty("name")
     // min and max range values
    @Size(min = 1, max = 255)
    private String name;

    @Getter
    @Setter
    @Column(name="EMPLOYEE_SALARY")
    @JsonProperty("salary")
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private Integer salary;

    @Getter
    @Setter
    @Column(name="DEPARTMENT")
    @JsonProperty("department")
    // min and max range values
    @Size(min = 1, max = 255)
    private String department;
}
