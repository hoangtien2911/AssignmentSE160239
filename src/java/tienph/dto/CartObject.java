/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienph.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Hp
 */
public class CartObject implements Serializable {
    private Map<ClothesDTO, Integer> items;

    public Map<ClothesDTO, Integer> getItems() {
        return items;
    }

    public void addItemToCart(ClothesDTO dto, int quantity) {
        //1. Check item is existed
        if (dto == null) {
            return;
        }

        //2. when item is existed, checking existed items
        if (this.items == null) {
            this.items = new HashMap<>();
            this.items.put(dto, quantity);
            return;
        }
        //3. items has existed, checking exiest id        
        if (this.items.containsKey(dto)) {
            quantity = this.items.get(dto) + quantity;
        }
        //4. Update item
        this.items.put(dto, quantity);
    }

    public void removeItemFromCart(ClothesDTO dto) {
        //1. Check exist items
        if (this.items == null) {
            return;
        }
        //when items has existed, check existed id
        if (this.items.containsKey(dto)) {            
            this.items.remove(dto);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }
    
    public int sizeCart() {
        if (this.items == null) {
            return 0;
        }
        return this.items.size();
    }

    public int getItemQuantity(ClothesDTO dto) {
       
        if (dto == null){
            return 0;
        }        
        if (this.items == null) {
            return 0;
        }        
        if (this.items.containsKey(dto)) {
            return this.items.get(dto);
        }
        return 0;
    }
    
    public boolean updateItemQuantity(ClothesDTO dto, int newQuantity) {
        if (dto == null) {
            return false;
        }
        if (this.items == null) {
            return false;
        }
        if (this.items.containsKey(dto)) {
            this.items.put(dto, newQuantity);
            return true;
        }
        return false;
    }
}
