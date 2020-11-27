package com.qa.ims.persistence.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest
{
    Customer customer = new Customer(1L, "jason", "fyfe");
    Customer noIDCustomer = new Customer("jason", "fyfe");

    @Test
    public void getId()
    {
        Long result = customer.getId();

        assertEquals(customer.getId(), result);
    }

    @Test
    public void setId()
    {
        customer.setId(2L);

        assertEquals(2L, (long) customer.getId());
    }

    @Test
    public void getFirstName()
    {
        String result = customer.getFirstName();

        assertEquals("jason", result);
    }

    @Test
    public void setFirstName()
    {
        customer.setFirstName("Dave");

        assertEquals("Dave", customer.getFirstName());
    }

    @Test
    public void getSurname()
    {
        String result = customer.getSurname();

        assertEquals("fyfe", result);
    }

    @Test
    public void setSurname()
    {
        customer.setSurname("Jones");

        assertEquals("Jones", customer.getSurname());
    }

    @Test
    public void testToString()
    {
        String result = customer.toString();

        assertEquals("id:1 first name:jason surname:fyfe", result);
    }

    @Test
    public void testEquals()
    {
        Customer test = new Customer(1L, "jason", "fyfe");
        boolean result = customer.equals(test);

        assertTrue(result);
    }
}