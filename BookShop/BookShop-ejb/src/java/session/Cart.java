/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import entity.CartItem;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.CreateException;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author sykie
 */
@Stateful
public class Cart implements CartLocal {
    @EJB
    private BooksFacadeLocal booksFacade;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private ArrayList<CartItem> cartItems;

    /**
     * Get the value of cartBooks
     *
     * @return the value of cartBooks
     */
    
    Cart() {
        this.cartItems = new ArrayList<>();
    }
    
    @Override
    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    @Override
    public void add(String isbn){
        boolean found = false;
        for (CartItem i:this.cartItems){
            if (i.getIsbn().equals(isbn)){
                i.setCount(i.getCount()+1);
                found = true;
                break;
            }
        }
        if (!found){
            CartItem n = new CartItem();
            n.setIsbn(isbn);
            n.setCount(1);
            cartItems.add(n);
            }
        
    }
    
    @Override
    public void set(String isbn, int num){
        for (CartItem i:cartItems){
            if(i.getIsbn().equals(isbn)){
                i.setCount(num);
            }
        }
    }

}