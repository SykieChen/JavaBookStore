/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entity.Books;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sykie
 */
@Local
public interface BooksFacadeLocal {

    void create(Books books);

    void edit(Books books);

    void remove(Books books);

    Books find(Object id);
    
    Books getByIsbn(String isbn);

    List<Books> findAll();

    List<Books> search(String key);
    
    List<Books> findRange(int[] range);

    int count();
    
}
