package com.qa.ims.persistence.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrderTest
{
    Item item = new Item(1L, "xbox", 199.99);
    List<Item> orderItems = new ArrayList<>();
    Order order;

    @Before
    public void setUp() throws Exception
    {
        orderItems.add(item);
        order = new Order(1L, 1L, orderItems);
    }

    @After
    public void tearDown() throws Exception
    {
        orderItems = new ArrayList<>();
        order = null;
    }

    @Test
    public void getId()
    {
        Long result = order.getId();

        assertEquals(order.getId(), result);
    }

    @Test
    public void setId()
    {
        order.setId(2L);

        assertEquals(2L, (long) order.getId());
    }

    @Test
    public void getCustomerID()
    {
        Long result = order.getCustomerID();

        assertEquals(1L, (long) result);
    }

    @Test
    public void setCustomerID()
    {
        order.setCustomerID(2L);

        assertEquals(2L, (long) order.getCustomerID());
    }

    @Test
    public void getOrderItems()
    {
        List<Item> result = order.getOrderItems();

        assertEquals(order.getOrderItems(), result);
    }

    @Test
    public void setOrderItems()
    {
        Item testItem = new Item(2L, "PS5", 69.69);
        List<Item> testItems = new ArrayList<>();
        testItems.add(testItem);

        order.setOrderItems(testItems);

        assertEquals(testItems, order.getOrderItems());
    }

    @Test
    public void getOrderTotal()
    {
    }

    @Test
    public void getItemQuantity()
    {
    }

    @Test
    public void getUniqueItems()
    {
    }

    @Test
    public void addItem()
    {
    }

    @Test
    public void removeItem()
    {
    }

    @Test
    public void testToString()
    {
    }

    @Test
    public void testEquals()
    {
    }
}