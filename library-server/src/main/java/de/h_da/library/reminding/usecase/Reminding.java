package de.h_da.library.reminding.usecase;

import javax.ejb.Local;


/**
 * [usecase] This use case comprises all functionality concerned with the
 * reminding / dunning process of the library.
 *
 */
@Local
public interface Reminding {

	/**
	 * [command] Daily batch to detect all overdue loans, generate reminders and
	 * corresponding invoices.
	 * 
	 * 
	 * 
	 * <pre>
	 *    [post 
	 *       foreach Loan l (l.dueDate &lt; current date  // book is overdue
	 *       &amp;&amp; !exists Reminder rem: rem.loan.equals(l)):  // and not yet reminded  
	 *         exists new Invoice i:  // has been newly created by this method
	 *           i.date is current date 
	 *           &amp;&amp; i.subject.equals(&quot;Library Reminder&quot;)
	 *           &amp;&amp; i.name.equals(l.customer.name)
	 *           &amp;&amp; i.address.equals(l.customer.address)
	 *           &amp;&amp; i.amount == 300  // EUR 3,00 reminder fee per book
	 *         &amp;&amp; exists new Reminder r: // has been newly created by this method
	 *           r.loan == l	// the loan that caused the reminder
	 *           &amp;&amp; r.fee == 300  // EUR 3,00 reminder fee per book
	 *           &amp;&amp; r.issueDate is current date
	 *           &amp;&amp; r.dueDate is current date + 20 days
	 *           &amp;&amp; r.invoiceId == i.id
	 *           &amp;&amp; r.status == ReminderStatus.ACTIVE
	 *         &amp;&amp; l.status == LoanStatus.DELAYED
	 *    ]      
	 * </pre>
	 * 
	 */
	public void sendReminders();

	/**
	 * [command] Closes a reminder, e.g. due to the reception of the reminder
	 * fee.
	 * 
	 * @param invoiceId
	 *            id of the invoice sent due to the reminder
	 * 
	 * <pre>
	 *     [pre exists Reminder r: r.invoiceId == invoiceId
	 *      pre r.status == ReminderStatus.ACTIVE
	 *      post r.status == ReminderStatus.CLOSED
	 *     ]
	 * </pre>
	 */
	public void closeReminder(Long invoiceId);
	
}
