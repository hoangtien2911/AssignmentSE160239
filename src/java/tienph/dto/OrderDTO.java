/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienph.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Hp
 */
public class OrderDTO implements Serializable {
    private int orderId;
    private Date orderDate;
    private Date shipDate;
    private int status;
    private int accId;    

    public OrderDTO() {
    }

    public OrderDTO(int orderId, Date orderDate, Date shipDate, int status, int accId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.status = status;
        this.accId = accId;
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
     * @return the orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the shipDate
     */
    public Date getShipDate() {
        return shipDate;
    }

    /**
     * @param shipDate the shipDate to set
     */
    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the accId
     */
    public int getAccId() {
        return accId;
    }

    /**
     * @param accId the accId to set
     */
    public void setAccId(int accId) {
        this.accId = accId;
    }

    @Override
    public String toString() {
        return "OrderDTO{" + "orderId=" + orderId + ", orderDate=" + orderDate + ", shipDate=" + shipDate + ", status=" + status + ", accId=" + accId + '}';
    }    
}
