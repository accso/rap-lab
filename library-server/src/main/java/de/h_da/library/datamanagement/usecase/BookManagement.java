package de.h_da.library.datamanagement.usecase;

import javax.ejb.Local;

import de.h_da.library.datamanagement.entity.Book;

/**
 * [usecase] This use case comprises all functionality used by library staff to
 * administer the book stock.
 * 
 */
@Local
public interface BookManagement {

	/**
	 * [command] Adds a new <code>Book</code> object and
	 * <code>numberOfBooksOnStock</code> new <code>BookOnStock</code>
	 * objects to the datamanagement component.
	 * 
	 * @param book
	 *            [in] a volatile book object, not yet stored
	 * @param numberOfBooksOnStock
	 *            [in] number of <code>BookOnStock</code> objects that are to
	 *            be created
	 * @return [out] the id of the persistent <code>Book</code> object
	 * 
	 * <pre>
	 *        [post exists Book b: b.title.equals(book.title)
	 *         analogous for all attributes of entity Book
	 *         post exist numberOfBookOnStock new instances BookOnStock boS: boS.book.equals(b)
	 *        ]
	 *   	
	 * </pre>
	 */
	public int addBook(Book book, int numberOfBooksOnStock);

	/**
	 * [command] Modifies an already stored <code>Book</code> object.
	 * 
	 * @param book
	 *            volatile book object with id
	 * 
	 * 
	 * <pre>
	 *        [pre exists Book b: b.id == book.id
	 *         post b.title.equals(book.title) 
	 *         analogous for all attributes of entity Book
	 *        ]
	 * </pre>
	 * 
	 * 
	 */
	public void modifyBook(Book book);

	/**
	 * [command] Creates new <code>BookOnStock</code> objects for an existing
	 * <code>Book</code>.
	 * 
	 * @param bookId
	 *            the id of the <code>Book</code> object for which new
	 *            <code>BookOnStock</code> objects are to be created
	 * @param numberOfBooksOnStock
	 *            number of <code>BookOnStock</code> objects that are to be
	 *            created
	 * 
	 * <pre>
	 *       [pre exists Book b: b.id == book.id
	 *         post exist numberOfBookOnStock additional instances BookOnStock boS: boS.book.equals(b)
	 *       ]
	 * </pre>
	 * 
	 */
	public void addBooksOnStock(int bookId, int numberOfBooksOnStock);
}
