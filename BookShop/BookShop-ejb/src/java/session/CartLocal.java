/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entity.CartItem;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sykie
 */
@Local
public interface CartLocal {
    
    List<CartItem> getCartItems();
    void add(String isbn);
    void set(String isbn, int num);
    
}
