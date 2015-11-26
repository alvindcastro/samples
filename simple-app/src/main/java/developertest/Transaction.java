package developertest;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Transaction {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private PaymentInstrument paymentInstrument;

	@Column
	private Date date;

	@Column
	private String subject;

	@Column
	private double amount;

	public PaymentInstrument getPaymentInstrument() {
		return paymentInstrument;
	}

	public void setPaymentInstrument(PaymentInstrument bankAccount) {
		this.paymentInstrument = bankAccount;
	}
}
