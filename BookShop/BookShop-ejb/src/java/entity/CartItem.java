/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

/**
 *
 * @author sykie
 */
public class CartItem {
   
    private String isbn;
    
    private int count;

    /**
     * Get the value of count
     *
     * @return the value of count
     */
    public int getCount() {
        return count;
    }

    /**
     * Set the value of count
     *
     * @param count new value of count
     */
    public void setCount(int count) {
        this.count = count;
    }


    /**
     * Get the value of isbn
     *
     * @return the value of isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Set the value of isbn
     *
     * @param isbn new value of isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    
}
