package com.qa.ims.controller;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OrderController implements CrudController<Order>
{
    public static final Logger LOGGER = LogManager.getLogger();

    private OrderDAO orderDAO;
    private ItemDAO itemDAO;
    private Utils utils;

    public OrderController(OrderDAO orderDAO, ItemDAO itemDAO, Utils utils) {
        super();
        this.orderDAO = orderDAO;
        this.itemDAO = itemDAO;
        this.utils = utils;
    }

    /**
     * Reads all orders to the logger
     */
    @Override
    public List<Order> readAll() {
        List<Order> orders = orderDAO.readAll();
        for (Order order : orders) {
            LOGGER.info(order.toString());
        }
        return orders;
    }

    /**
     * Creates an order by taking in user input
     */
    @Override
    public Order create()
    {
        List<Item> orderItems = new ArrayList<>();
        LOGGER.info("Please enter the customer ID for the order");
        Long customerID = utils.getLong();

        boolean addNewItem;
        do
        {
            LOGGER.info("Please enter the item ID to add to the order");
            Long itemID = utils.getLong();

            orderItems.add(itemDAO.readItem(itemID));

            LOGGER.info("Add another item\nYes | No");
            String choice = utils.getString();

            addNewItem = choice.toLowerCase().equals("yes");
        }
        while (addNewItem);

        Order order = orderDAO.create(new Order(customerID, orderItems));

        LOGGER.info("Order created");
        return order;
    }

    /**
     * Updates an existing order by taking in user input
     */
    @Override
    public Order update() {

        LOGGER.info("Please enter the id of the order you would like to update");
        Long id = utils.getLong();

        Order order = orderDAO.readOrder(id);

        boolean editItem = true;
        do
        {
            LOGGER.info("Add: Add an Item to the order");
            LOGGER.info("Remove: Remove an Item from the order");
            LOGGER.info("Return: Return to previous menu");
            String choice = utils.getString().toLowerCase();

            switch (choice)
            {
                case "add":
                    LOGGER.info("The Item ID to add to the order");
                    long addID = utils.getLong();
                    Item item = itemDAO.readItem(addID);
                    order.addItem(item);
                    break;
                case "remove":
                    LOGGER.info("The Item ID to remove from the order");
                    long removeID = utils.getLong();
                    order.removeItem(itemDAO.readItem(removeID));
                    break;
                case "return":
                    editItem = false;
                    break;
            }
        }
        while (editItem);

        LOGGER.info("Order Updated");
        return orderDAO.update(order);
    }

    /**
     * Deletes an existing order by the id of the order
     */
    @Override
    public int delete() {
        LOGGER.info("Please enter the id of the order you would like to delete");
        Long id = utils.getLong();
        return orderDAO.delete(id);
    }
}
