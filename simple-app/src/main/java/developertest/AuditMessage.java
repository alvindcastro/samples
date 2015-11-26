package developertest;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class AuditMessage {

	@Id
	@GeneratedValue
	private long id;

	@Column
	private String userName;

	@Column
	private Date date;

	@Column
	private String message;
	
}
