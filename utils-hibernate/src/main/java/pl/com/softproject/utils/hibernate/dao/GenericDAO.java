package pl.com.softproject.utils.hibernate.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * Genieric DAO interface
 *
 * @author Adrian Lapierre 
 * @param <T> - domain object type
 * @param <PK> - primary key type
 */
public interface GenericDAO <T, PK extends Serializable> {

    /** Persist the newInstance object into database */
    PK create(T newInstance);

    /** Retrieve an object that was previously persisted to the database using
     *   the indicated id as primary key
     */
    T load(PK id);

    /** 
     * Save changes made to a persistent object.  
     */
    void update(T transientObject);
    
    /** 
     * Save changes made to a persistent object.  
     */
    public void saveOrUpdate(T o);

    /** Remove an object from persistent storage in the database */
    void delete(T persistentObject);
    
    void flush();
    
    /**
     * returns all entity
     * @return all entity list
     */
    public List<T> searchAll();

    /**
     *
     * @param sortProperity
     * @param ascending
     * @return
     */
    public List<T> searchAllOrdered(String sortProperity, boolean ascending);
    
    public List<T> searchAllOrdered(final String sortProperity, final boolean ascending, final boolean distinct);
    
    /**
     * Wyszukuje encje według podanego przykładu. Wszystkie właściwości, różne od null i 0 
     * będą brane pod uwagę. Do porównywania stringów używany jest operator like, wielkość znaków
     * jest ignorowana.
     * 
     * @param exampleEntity przykładowa encja
     * @return lista encji
     */
    public List<T> searchByExample(final T exampleEntity);
    
    /**
     * Wyszukuje encje według podanego przykładu i zwraca posortowaną listę
     * @param exampleEntity przykładowa encja
     * @param sortProperity nazwa właściwości po któej ma się sotrować
     * @param ascending czy rosnąco czy malejąco
     * @return
     */
    public List<T> searchByExample(final T exampleEntity, String sortProperity, boolean ascending);
    
    /**
     * Wyszukuje encje według podanego przykładu. Właściwości o nazwach podanych w  excludeProperty
     * nie będą porównywane.
     * 
     * @param exampleEntity przykładowa encja
     * @param excludeProperty lista właściwości, które mają nie być uwzględnione
     * @return lista encji
     */
    public List<T> searchByExample(final T exampleEntity, List<String> excludeProperty);

    void persist(T o);

    List<T> searchByCriteria(DetachedCriteria criteria);

    List<T> searchByHQLQuery(String query, Object... params);
    
    Object executeHibernateCallback(HibernateCallback callback);
}