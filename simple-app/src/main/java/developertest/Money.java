package developertest;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Money {

	private final BigDecimal amount;
	private final String currency;

	public Money(BigDecimal amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}

	public Money add(Money what) {
		return new Money(amount.add(what.getAmount()), currency);
	}

	public Money subtract(Money what) {
		return new Money(amount.subtract(what.getAmount()), currency);
	}
}
