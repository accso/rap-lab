package de.h_da.library.datamanagement.usecase;

import java.util.Collection;

import de.h_da.library.datamanagement.entity.Customer;
import javax.ejb.*;

/**
 * [usecase] This use case comprises all functionality used by library staff to
 * administer the customer base.
 * 
 */
@Local
public interface CustomerManagement {

	/**
	 * [command] Adds a new <code>Customer</code> object 
	 * to the datamanagement component.
	 * 
	 * @param customer
	 *         [in] a volatile customer object, not yet stored
	 * @return [out] the id of the new persistent <code>Customer</code> object
	 * 
	 * <pre>
	 *        [pre !exists Customer c: c.id == result
	 *         post exists new Customer c: 
	 *         c.id == result
	 *         && c.name.equals(customer.name)
	 *         && analogous for all attributes of entity customer
	 *        ]
	 *   	
	 * </pre>
	 */
	public int newCustomer(Customer customer);

	/**
	 * [command] Modifies an already stored <code>Customer</code> object.
	 * 
	 * @param customer
	 *            volatile customer object with id
	 * 
	 * 
	 * <pre>
	 *        [pre exists Customer c: c.id == customer.id
	 *         post c.name.equals(customer.name) 
	 *         analogous for all attributes of entity customer
	 *        ]
	 * </pre>
	 * 
	 * 
	 */
	public void modifyCustomer(Customer customer);

	/**
	 * Searches the database for a customer instance with a given id
	 * 
	 * @param id the unique customer identifier
	 * @return Customer instance if customer found with corresponding id,
	 *         null, otherwise
	 *         
	 * <pre>
	 * [post result.id == id || result == null]
	 * </pre>
	 */
	public Customer findCustomerById(int id);

	
	/**
	 * [basicQuery] Finds all customers in the datamanagement component according 
	 * to a given search template.
	 * 
	 * @param customer
	 *            [in] volatile <code>Customer</code> object as a search template
	 * @return [out] Collection of all <code>Customer</code> objects in the datamanagement
	 *         component of which attributes match the search template (substring
	 *         comparison, case-insensitive)
	 * 
	 * <pre>
	 *    [post forall Customer c in result: 
	 *            c.name.toUpperCase().contains(customer.name.toUpperCase()) if customer.name != null 
	 *     analogous for all other attributes of entity customer
	 *    ]
	 * </pre>
	 * 
	 */
	public Collection<Customer> findCustomersByAttributes(Customer customer);
	
//	public Collection<Customer> findCustomerByAttributeName(String name);
//	
//	public Collection<Customer> findCustomerByAttributeAddress(String address);
}
