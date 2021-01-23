package vn.kase.onlineExam.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_subject")
public class Subject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
  @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

	@Column(name = "subject_name")
	@NotNull
	@NotBlank(message= "Subject name cannot be null !!!")
	private String subjectName;

	@Column(name = "start_date")
	@NotNull(message= "Start date cannot be null !!!")
	private Date startDate;

	@Column(name = "end_date")
	@NotNull(message= "End date cannot be null !!!")
	private Date endDate;    
	
	@Column(name="times")
	private Integer time;
}
