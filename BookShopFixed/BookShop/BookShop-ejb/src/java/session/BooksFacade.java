/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entity.Books;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author sykie
 */
@Stateless
public class BooksFacade extends AbstractFacade<Books> implements BooksFacadeLocal {
    @PersistenceContext(unitName = "BookShop-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BooksFacade() {
        super(Books.class);
    }
    
    @Override
    public List<Books> search(String key){
        return em.createQuery("SELECT c FROM Books c WHERE c.title like :key or c.author like :key or c.press like :key").setParameter("key", "%" + key + "%").getResultList();
    }
    
    @Override
    public Books getByIsbn(String isbn){
        return (Books)em.createQuery("SELECT c FROM Books c WHERE c.isbn = :isbn").setParameter("isbn",isbn).getSingleResult();
    }
    
}
