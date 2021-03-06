package developertest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentInstrumentServiceImpl implements PaymentInstrumentService {

	@Autowired
	private PaymentInstrumentRepository paymentInstrumentRepository;

	@Autowired
	private AuditMessageRepository auditMessageRepository;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void createAuditMessage(String message) {
		AuditMessage auditMessage = new AuditMessage();
		auditMessage.setDate(new Date());
		auditMessage.setMessage(message);
		auditMessage.setUserName("Dummy");
		auditMessageRepository.save(auditMessage);
	}

	@Override
	@Transactional
	public List<PaymentInstrument> findPaymentInstruments() {
		Iterable<PaymentInstrument> paymentInstruments = paymentInstrumentRepository.findAll();
		List<PaymentInstrument> result = new ArrayList<PaymentInstrument>();
		for (PaymentInstrument paymentInstrument : paymentInstruments) {
			result.add(paymentInstrument);
		}
		return result;
	}

	@Override
	@Transactional
	public void lockPaymentInstrument(long paymentInstrumentId) {
		createAuditMessage(String.format("Locks payment instrument %s", paymentInstrumentId));
		PaymentInstrument paymentInstrument = paymentInstrumentRepository.findOne(paymentInstrumentId);
		if (paymentInstrument.getStatus() == PaymentInstrumentStatus.Locked) {
			throw new IllegalStateException(
					String.format("Payment instrument %s is already locked!", paymentInstrumentId));
		}
		paymentInstrument.setStatus(PaymentInstrumentStatus.Locked);
	}

	@Override
	@Transactional
	public void unlockPaymentInstrument(long paymentInstrumentId) {
		createAuditMessage(String.format("Unlocks payment instrument %s", paymentInstrumentId));
		PaymentInstrument paymentInstrument = paymentInstrumentRepository.findOne(paymentInstrumentId);
		if (paymentInstrument.getStatus() == PaymentInstrumentStatus.Active) {
			throw new IllegalStateException(
					String.format("Payment instrument %s is already active!", paymentInstrumentId));
		}
		paymentInstrument.setStatus(PaymentInstrumentStatus.Active);
	}

	@Override
	@Transactional
	public long createBankAccount(String accountHolder, String bankName, String iban, String bic) {
		BankAccount bankAccount = new BankAccount();
		bankAccount.setStatus(PaymentInstrumentStatus.Active);
		bankAccount.setCreationDateTime(new Date());
		bankAccount.setAccountHolder(accountHolder);
		bankAccount.setBankName(bankName);
		bankAccount.setIban(iban);
		bankAccount.setBic(bic);
		PaymentInstrument saved = paymentInstrumentRepository.save(bankAccount);
		return saved.getId();
	}

	@Override
	@Transactional
	public long createCreditCard(String accountHolder, CreditCardScheme scheme, String pan, int expiryMonth,
			int expiryYear) {
		CreditCard creditCard = new CreditCard();
		creditCard.setStatus(PaymentInstrumentStatus.Active);
		creditCard.setCreationDateTime(new Date());
		creditCard.setAccountHolder(accountHolder);
		creditCard.setScheme(scheme);
		creditCard.setPan(pan);
		creditCard.setExpiryMonth(expiryMonth);
		creditCard.setExpiryYear(expiryYear);
		PaymentInstrument saved = paymentInstrumentRepository.save(creditCard);
		return saved.getId();
	}

}
