package de.h_da.library.datamanagement.usecase;

import java.util.Collection;

import javax.ejb.Local;

import de.h_da.library.datamanagement.entity.Book;
import de.h_da.library.datamanagement.entity.Customer;

/**
 * [usecase] This use case comprises all functionality for library customers to
 * search the library catalogue.
 * 
 */
@Local
public interface Search {

	/**
	 * [basicQuery] Finds all books in the library system according to a given template.
	 * 
	 * @param book
	 *            [in] volatile <code>Book</code> object as search template
	 * @return [out] Collection of all <code>Book</code> objects in the library
	 *         system whose attributes match the search template (substring
	 *         comparison, case-insensitive)
	 * 
	 * <pre>
	 *    [post forall Book b in result: 
	 *            b.title.toUpperCase().contains(book.title.toUpperCase()) if book.title != null 
	 *     analogous for all other attributes of entity book
	 *    ]
	 * </pre>
	 * 
	 */
	public Collection<Book> findBooksByAttributes(Book book);

	/**
	 * [basicQuery] Finds all customers in the library system according to a given template.
	 * 
	 * @param customer
	 *            [in] volatile <code>Customer</code> object as search template
	 * @return [out] Collection of all <code>Customer</code> objects in the library
	 *         system whose attributes match the search template (substring
	 *         comparison, case-insensitive)
	 * 
	 * <pre>
	 *    [post forall Customer c in result: 
	 *            c.name.toUpperCase().contains(customer.name.toUpperCase()) if customer.name != null 
	 *     analogous for all other attributes of entity book
	 *    ]
	 * </pre>
	 * 
	 */
	public Collection<Customer> findCustomersByAttributes(Customer customer);

}
