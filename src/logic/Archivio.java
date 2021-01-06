package logic;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Class that stores and manages an archive of shipments and insured shipments.
 * 
 * @author &#160; &#160; Castorini Francesco
 * @see spedizione.Spedizione
 * @see spedizione.SpedizioneAssicurata
 * @param <E> The type of items for this archive
 */
public class Archivio<E> implements Collection<E>, Serializable {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * AGGIUNGERE !!!
	 */
	private List<E> spedizione; //utilizzo la regola di conformit� 
	
	/**
	 *
	 */
	public Archivio() {
		spedizione = new Vector<E>();
	}
	
	/**
	 * Default method.
	 */
	@Override
	public int size() {
		return spedizione.size();
	}
	
	/**
	 * Default method.
	 */
	@Override
	public boolean isEmpty() {
		return spedizione.isEmpty();
	}

	/**
	 * Default method.
	 */
	@Override
	public boolean contains(Object o) {
		return spedizione.contains(o);
	}

	/**
	 * Default method.
	 */
	@Override
	public Iterator<E> iterator() {
		return spedizione.iterator();
	}

	/**
	 * Default method.
	 */
	@Override
	public Object[] toArray() {
		return spedizione.toArray();
	}

	/**
	 * Default method.
	 */
	@Override
	public <T> T[] toArray(T[] a) {
		return spedizione.toArray(a);
	}

	/**
	 * Default method.
	 */
	@Override
	public boolean add(E e) {
		return spedizione.add(e);
	}

	/**
	 * Default method.
	 */
	@Override
	public boolean remove(Object o) {
		return spedizione.remove(o);
	}

	/**
	 * Default method.
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		return spedizione.containsAll(c);
	}

	/**
	 * Default method.
	 */
	@Override
	public boolean addAll(Collection<? extends E> c) {
		return spedizione.addAll(c);
	}

	/**
	 * Default method.
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		return spedizione.removeAll(c);
	}

	/**
	 * Default method.
	 */
	@Override
	public boolean retainAll(Collection<?> c) {
		return spedizione.retainAll(c);
	}

	/**
	 * Default method.
	 */
	@Override
	public void clear() {
		spedizione.clear();
	}
	
	/**
	 * Default method.
	 */
	@Override
	public String toString() {
		return spedizione.toString();
	}

	/**
	 * Returns the element at the specified position in this archive.
	 * @param index index of the element to return
	 * @return the element at the specified position in this archive
	 */
	public E get(int index) {
		return spedizione.get(index);
	}
	
	/**
	 * Replaces the element at the specified position in this archive with the specified element.
	 * @param index index of the element to replace
	 * @param e element to be stored at the specified position
	 * @return the element previously at the specified position
	 */
	public E set(int index, E e) {
		spedizione.set(index, e);
		return e;
	}
	
	/**
	 * Removes the element at the specified position in this archive.
	 * @param index the index of the element to be removed
	 * @return the element previously at the specified position
	 */
	public E remove(int index) {
		return spedizione.remove(index);
	}
}
