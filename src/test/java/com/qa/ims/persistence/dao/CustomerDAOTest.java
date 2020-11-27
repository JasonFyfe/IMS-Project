package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerDAOTest
{

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private CustomerDAO dao;

    @Test
    public void modelFromResultSet() throws SQLException
    {
        Customer customer = new Customer(1L, "jason", "fyfe");
        Mockito.when(resultSet.getLong("id")).thenReturn(customer.getId());
        Mockito.when(resultSet.getString("first_name")).thenReturn(customer.getFirstName());
        Mockito.when(resultSet.getString("surname")).thenReturn(customer.getSurname());

        assertEquals(customer, dao.modelFromResultSet(resultSet));
    }

    @Test
    public void readAll()
    {
        assertNotNull(dao.readAll());
    }

    @Test
    public void readLatest()
    {
    }

    @Test
    public void create()
    {
    }

    @Test
    public void readCustomer()
    {
    }

    @Test
    public void update()
    {
    }

    @Test
    public void delete()
    {
    }
}