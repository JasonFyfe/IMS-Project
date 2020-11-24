package com.qa.ims.persistence.domain;

import java.util.HashMap;

public class Order
{
    private Long id;
    private Long customerID;
    private HashMap<Long, Integer> orderItems;

    public Order(Long customerID, HashMap<Long, Integer> orderItems) {
        this.customerID = customerID;
        this.orderItems = orderItems;
    }

    // todo remove once hashmap is implemented
    public Order(Long id, Long customerID) {
        this.id = id;
        this.customerID = customerID;
    }
    // todo remove once hashmap is implemented
    public Order(Long customerID)
    {
        this.customerID = customerID;
    }

    public Order(Long id, Long customerID, HashMap<Long, Integer> orderItems) {
        this.id = id;
        this.customerID = customerID;
        this.orderItems = orderItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public HashMap<Long, Integer> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(HashMap<Long, Integer> orderItems) {
        this.orderItems = orderItems;
    }

    // todo Iterate through and print all item names + prices, and print customer details.
    @Override
    public String toString() {
        // todo fix toString override once hashmap is implemented
//        return "id:" + id + " Customer ID:" + customerID + " Items:" + orderItems.size();
        return "id:" + id + " Customer ID:" + customerID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (customerID == null) {
            if (other.customerID != null)
                return false;
        } else if (!customerID.equals(other.customerID))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (orderItems == null) {
            if (other.orderItems != null)
                return false;
        } else if (!orderItems.equals(other.orderItems))
            return false;
        return true;
    }

}
