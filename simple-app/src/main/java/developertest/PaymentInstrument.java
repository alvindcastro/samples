package developertest;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public abstract class PaymentInstrument {

	private transient PaymentInstrumentType type;

	@Id
	@GeneratedValue
	private long id;

	@Column
	private PaymentInstrumentStatus status;

	@Column
	private Date creationDateTime;

	@Column
	private String accountHolder;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "paymentInstrument")
	private List<Transaction> transactions;

	public PaymentInstrument(PaymentInstrumentType type) {
		this.type = type;
	}

	public PaymentInstrumentType getType() {
		return type;
	}

	public void setType(PaymentInstrumentType type) {
		this.type = type;
	}

	public PaymentInstrumentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentInstrumentStatus status) {
		this.status = status;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public abstract String getDescription();

}
