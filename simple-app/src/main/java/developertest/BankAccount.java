package developertest;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class BankAccount extends PaymentInstrument {

	@Column
	private String bankName;

	@Column
	private String iban;

	@Column
	private String bic;

	public BankAccount() {
		super(PaymentInstrumentType.BankAccount);
	}

	public String getDescription() {
		return String.format("Bank Account %s (%s)", getId(),
				getStatus() == PaymentInstrumentStatus.Active ? "Active" : "Locked");
	}
}
