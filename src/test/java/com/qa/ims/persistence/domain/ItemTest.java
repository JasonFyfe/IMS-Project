package com.qa.ims.persistence.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest
{
    Item item = new Item(1L, "xbox", 299.99);
    Item noIDItem = new Item("PS5", 150.50);
    private static final double DELTA = 1e-15;

    @Test
    public void getId()
    {
        Long result = item.getId();

        assertEquals(item.getId(), result);
    }

    @Test
    public void setId()
    {
        item.setId(2L);

        assertEquals(2L, (long) item.getId());
    }

    @Test
    public void getName()
    {
        String result = item.getName();

        assertEquals("xbox", result);
    }

    @Test
    public void setName()
    {
        item.setName("Switch");

        assertEquals("Switch", item.getName());
    }

    @Test
    public void getPrice()
    {
        double result = item.getPrice();

        assertEquals(299.99, result, DELTA);
    }

    @Test
    public void setPrice()
    {
        item.setPrice(199.00);

        assertEquals(199.00, (double) item.getPrice(), DELTA);
    }

    @Test
    public void testToString()
    {
        String result = item.toString();

        assertEquals("Item ID: 1 | Item Name:xbox | Price:299.99", result);
    }

    @Test
    public void testEquals()
    {
        Item test = new Item(1L, "xbox", 299.99);
        boolean result = item.equals(test);

        assertTrue(result);
    }
}