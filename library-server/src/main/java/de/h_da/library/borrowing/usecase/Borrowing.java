package de.h_da.library.borrowing.usecase;

import javax.ejb.Local;

import de.h_da.library.LibraryException;

/**
 * [usecase] This use case comprises all library customer functionality for
 * borrowing and returning books.
 * 
 */
@Local
public interface Borrowing {

	/**
	 * [command] Generates a new <code>Loan</code> object.
	 * 
	 * @param bookOnStockId
	 *            [in] id of <code>BookOnStock</code> to be borrowed
	 * @param customerId
	 *            [in] id of <code>Customer</code> who borrows the book
	 * @return [out] id of generated <code>Loan</code> object
	 * 
	 * <pre>
	 *    [pre exists Customer c: c.id == customerId
	 *     pre exists BookOnStock boS: boS.id == bookOnStockId
	 *     post exists new Loan l:
	 *       l.id == result
	 *       &amp;&amp; l.loanDate is current date
	 *       &amp;&amp; l.dueDate is current date plus 30 days
	 *       &amp;&amp; l.customer.id == customerId
	 *       &amp;&amp; l.bookOnStock.id == bookOnStockId
	 *       &amp;&amp; l.reminder == null
	 *       &amp;&amp; l.status == LoanStatus.BORROWED
	 *     ]
	 * </pre>
	 * @throws LibraryException 
	 */
	public int borrowBook(Long bookOnStockId, Long customerId) throws LibraryException;

	/**
	 * [command] Marks a <code>Loan</code> as being returned.
	 * 
	 * @param loanId
	 *            [in] id of <code>Loan</code> object
	 * 
	 * <pre>
	 *   [pre exists Loan l (l.id == loanId)
	 *    pre l.status != LoanStatus.RETURNED
	 *    post l.status == LoanStatus.RETURNED
	 *   ]
	 * </pre>
	 */
	public void returnBook(Long loanId);

}
