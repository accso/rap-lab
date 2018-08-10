package de.h_da.library.accounting.usecase;

import java.util.Collection;

import javax.ejb.Local;




/**
 * [usecase] This use case comprises the functionality of the accounting
 * component.
 * 
 */
@Local
public interface Accounting {

	/**
	 * Generates a new <code>Invoice</code> object.
	 * 
	 * @param invoiceSubject
	 *            [in] invoice subject
	 * @param invoiceName
	 *            [in] name of the invoice's recipient
	 * @param invoiceAddress
	 *            [in] address where the invoice is to be sent to
	 * @param invoiceAmount
	 *            [in] invoice amount in Euro cents
	 * @return [out] id of the generated <code>Invoice</code> object
	 * 
	 * <pre>
	 *       [post exists new Invoice i: i.id == result 
	 *        post i.date is current date
	 *        post i.subject == invoiceSubject
	 *        post i.name == invoiceName
	 *        post i.address == invoiceAddress
	 *        post i.amount == invoiceAmount
	 *        post i.status == InvoiceStatus.SENT
	 *       ]
	 * </pre>
	 */
	public int sendInvoice(String invoiceSubject, String invoiceName, String invoiceAddress,
			int invoiceAmount);

	/**
	 * Notifies the accounting component that the money corresponding to an
	 * invoice has been received and, hence, the invoice can be closed
	 * 
	 * @param invoiceId
	 *            [in] the id of the invoice to be closed
	 * 
	 * <pre>
	 *     [post Invoice i (i.id == invoiceID): i.invoiceStatus == InvoiceStatus.PAID
	 * </pre>
	 */
	public void receiveMoney(Long invoiceId);
	


}