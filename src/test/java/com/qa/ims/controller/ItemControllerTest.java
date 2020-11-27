package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest
{
    @Mock
    private Utils utils;

    @Mock
    private ItemDAO dao;

    @InjectMocks
    private ItemController controller;

    @Test
    public void readAll()
    {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1L, "dreamcast", 149.99));

        Mockito.when(dao.readAll()).thenReturn(items);

        assertEquals(items, controller.readAll());
    }

    @Test
    public void create()
    {
        final String NAME = "Megadrive";
        final Double PRICE = 256.34;
        final Item created = new Item(NAME, PRICE);

        Mockito.when(utils.getString()).thenReturn(NAME);
        Mockito.when(utils.getDouble()).thenReturn(PRICE);

        Mockito.when(dao.create(created)).thenReturn(created);

        assertEquals(created, controller.create());

        Mockito.verify(utils, Mockito.times(1)).getString();
        Mockito.verify(utils, Mockito.times(1)).getDouble();
    }

    @Test
    public void update()
    {
        Item updated = new Item(1L, "Master System", 59.32);

        Mockito.when(this.utils.getLong()).thenReturn(1L);
        Mockito.when(this.utils.getString()).thenReturn(updated.getName());
        Mockito.when(this.utils.getDouble()).thenReturn(updated.getPrice());
        Mockito.when(this.dao.update(updated)).thenReturn(updated);

        assertEquals(updated, this.controller.update());

        Mockito.verify(this.utils, Mockito.times(1)).getLong();
        Mockito.verify(this.utils, Mockito.times(1)).getString();
        Mockito.verify(this.utils, Mockito.times(1)).getDouble();
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