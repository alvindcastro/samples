package developertest;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class CreditCard extends PaymentInstrument {

	@Column
	private String pan;

	@Column
	private CreditCardScheme scheme;

	@Column
	private int expiryMonth;

	@Column
	private int expiryYear;

	public CreditCard() {
		super(PaymentInstrumentType.CreditCard);
	}

	public CreditCardScheme getScheme() {
		return scheme;
	}

	public void setScheme(CreditCardScheme scheme) {
		this.scheme = scheme;
	}

	@Override
	public String getDescription() {
		return String.format("Credit Card %s (%s)", getId(),
				getStatus() == PaymentInstrumentStatus.Active ? "Active" : "Locked");
	}

}
