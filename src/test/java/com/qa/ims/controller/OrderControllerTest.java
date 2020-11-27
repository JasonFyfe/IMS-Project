package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest
{
    @Mock
    private Utils utils;

    @Mock
    private OrderDAO dao;

    @Mock
    private ItemDAO itemDAO;

    @InjectMocks
    private OrderController controller;

    @Test
    public void readAll()
    {
        List<Order> orders = new ArrayList<>();
        List<Item> items = new ArrayList<>();
        items.add(new Item(1L, "Xbox", 299.00));

        orders.add(new Order(1L, 1L, items));

        Mockito.when(dao.readAll()).thenReturn(orders);

        assertEquals(orders, controller.readAll());

        Mockito.verify(dao, Mockito.times(1)).readAll();
    }

    @Test
    public void create()
    {
        final Long CUSTOMER_ID = 1L;
        final Long ITEM_ID = 1L;
        final Item ITEM = new Item(ITEM_ID, "xbox", 299.99);
        List<Item> orderItems = new ArrayList<>();
        orderItems.add(ITEM);
        final String RESPONSE = "no";
        final Order created = new Order(CUSTOMER_ID, orderItems);

        Mockito.when(utils.getLong()).thenReturn(CUSTOMER_ID);
        Mockito.when(utils.getLong()).thenReturn(ITEM_ID);

        Mockito.when(itemDAO.readItem(ITEM_ID)).thenReturn(ITEM);

        Mockito.when(utils.getString()).thenReturn(RESPONSE);

        Mockito.when(dao.create(created)).thenReturn(created);

        assertEquals(created, controller.create());

        Mockito.verify(utils, Mockito.times(2)).getLong();
        Mockito.verify(utils, Mockito.times(1)).getString();
        Mockito.verify(itemDAO, Mockito.times(1)).readItem(ITEM_ID);
        Mockito.verify(dao, Mockito.times(1)).create(created);
    }

    @Test
    public void update_add()
    {
    }

    @Test
    public void update_remove()
    {
    }

    @Test
    public void update_return()
    {
        final Item ITEM = new Item(1L, "xbox", 299.99);
        List<Item> orderItems = new ArrayList<>();
        orderItems.add(ITEM);

        final Order updated = new Order(1L,1L, orderItems);

        Mockito.when(this.utils.getLong()).thenReturn(1L);
        Mockito.when(this.dao.readOrder(updated.getId())).thenReturn(updated);

        Mockito.when(this.utils.getString()).thenReturn("return");

        Mockito.when(this.dao.update(updated)).thenReturn(updated);

        assertEquals(updated, this.controller.update());
    }

    @Test
    public void delete()
    {
        final long ID = 1L;

        Mockito.when(utils.getLong()).thenReturn(ID);
        Mockito.when(dao.delete(ID)).thenReturn(1);

        assertEquals(1L, this.controller.delete());

        Mockito.verify(utils, Mockito.times(1)).getLong();
        Mockito.verify(dao, Mockito.times(1)).delete(ID);
    }
}