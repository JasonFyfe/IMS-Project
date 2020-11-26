package com.qa.ims.persistence.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Order
{
    private Long id;
    private Long customerID;
    private List<Item> orderItems;
    private double orderTotal;

    public Order(Long customerID, List<Item> orderItems)
    {
        this.customerID = customerID;
        this.orderItems = orderItems;
        updateOrderTotal();
    }

    public Order(Long id, Long customerID, List<Item> orderItems)
    {
        this.id = id;
        this.customerID = customerID;
        this.orderItems = orderItems;
        updateOrderTotal();
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getCustomerID()
    {
        return customerID;
    }

    public void setCustomerID(Long customerID)
    {
        this.customerID = customerID;
    }

    public List<Item> getOrderItems()
    {
        return orderItems;
    }

    public void setOrderItems(List<Item> orderItems)
    {
        this.orderItems = orderItems;
    }

    public double getOrderTotal()
    {
        return orderTotal;
    }

    private void updateOrderTotal()
    {
        for (Item i : orderItems)
        {
            orderTotal += i.getPrice();
        }
    }

    public int getItemQuantity(Item item)
    {
        return Collections.frequency(orderItems, item);
    }

    public HashMap<Integer, Item> getUniqueItems()
    {
        HashMap<Integer, Item> uniqueOrderItems = new HashMap<>();

        for (Item i : orderItems)
        {
            uniqueOrderItems.put(i.getId().intValue(), i);
        }

        return uniqueOrderItems;
    }

    public void addItem(Item item)
    {
        orderItems.add(item);
    }

    public void removeItem(Item item)
    {
        orderItems.remove(item);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(id).append(" | Customer ID: ").append(customerID).append(" | Order Total: ").append(orderTotal);
        HashMap<Integer, Item> uniqueItems = getUniqueItems();
        if (orderItems != null)
        {
            uniqueItems.forEach((k,item) -> {
                sb.append("\n  --").append(item.toString()).append(" | Quantity: ").append(getItemQuantity(item));
            });
        }
        else
        {
            sb.append("\n This order has no items!");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }
        Order other = (Order) obj;

        if (customerID == null)
        {
            if (other.customerID != null) { return false; }
        }
        else if (!customerID.equals(other.customerID)) { return false; }

        if (id == null)
        {
            if (other.id != null) { return false; }
        }
        else if (!id.equals(other.id)) { return false; }
        if (orderItems == null)
        {
            if (other.orderItems != null) { return false; }
        }
        else if (!orderItems.equals(other.orderItems)) { return false; }
        return true;
    }
}
