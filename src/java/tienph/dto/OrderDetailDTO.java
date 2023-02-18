package tienph.dto;


import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hp
 */
public class OrderDetailDTO implements Serializable {
    private int detailId;
    private int orderId;
    private int clothID;
    private int quantity;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int detailId, int orderId, int clothID, int quantity) {
        this.detailId = detailId;
        this.orderId = orderId;
        this.clothID = clothID;
        this.quantity = quantity;
    }

    /**
     * @return the detailId
     */
    public int getDetailId() {
        return detailId;
    }

    /**
     * @param detailId the detailId to set
     */
    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    /**
     * @return the orderId
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the clothID
     */
    public int getClothID() {
        return clothID;
    }

    /**
     * @param clothID the clothID to set
     */
    public void setClothID(int clothID) {
        this.clothID = clothID;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" + "detailId=" + detailId + ", orderId=" + orderId + ", clothID=" + clothID + ", quantity=" + quantity + '}';
    }
    
}
