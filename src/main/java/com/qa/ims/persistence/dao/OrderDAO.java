package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;
import com.qa.ims.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderDAO implements Dao<Order>
{
    public static final Logger LOGGER = LogManager.getLogger();
    private ItemDAO itemDAO = new ItemDAO();
    private Utils utils = new Utils();
    private HashMap<Long, Long> orderMap = new HashMap<>();

    @Override
    public List<Order> readAll()
    {
        List<Order> allOrders = new ArrayList<>();
        String ordersSQL = "SELECT * FROM orders";
        String itemsSQL = "SELECT items.* FROM order_items LEFT JOIN items ON order_items.itemID = items.id WHERE orderID = ";

        // get all orderID and customerID
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(ordersSQL))
        {
            while(resultSet.next())
            {
                appendToOrderMap(resultSet);
            }
        }
        catch (SQLException e)
        {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }

        // get all items for each order
        orderMap.forEach((orderID, customerID) -> {
            List<Item> allItems = new ArrayList<>();

            try (Connection connection = DBUtils.getInstance().getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(itemsSQL + orderID))
            {
                while(resultSet.next())
                {
                    allItems.add(modelItemFromResultSet(resultSet));
                }
            }
            catch (SQLException e)
            {
                LOGGER.debug(e);
                LOGGER.error(e.getMessage());
            }

            allOrders.add(new Order(orderID, customerID, allItems));
        });

        orderMap.clear();
        return allOrders;
    }

    public Order readLatest()
    {
        Long orderID;
        Long customerID;
        List<Item> orderItems = new ArrayList<>();
        String itemsSQL = "SELECT items.* FROM order_items LEFT JOIN items ON order_items.itemID = items.id WHERE orderID = ";

        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id AS orderID, customerID FROM orders ORDER BY id DESC LIMIT 1"))
        {
            resultSet.next();
            appendToOrderMap(resultSet);
        }
        catch (Exception e)
        {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }

        orderMap.forEach((order, customer) -> {
            try (Connection connection = DBUtils.getInstance().getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(itemsSQL + order))
            {
                while(resultSet.next())
                {
                    orderItems.add(modelItemFromResultSet(resultSet));
                }
            }
            catch (SQLException e)
            {
                LOGGER.debug(e);
                LOGGER.error(e.getMessage());
            }
        });

        orderID = (Long) orderMap.keySet().toArray()[0];
        customerID = orderMap.get(orderID);

        orderMap.clear();
        return new Order(orderID, customerID, orderItems);
    }

    @Override
    public Order create(Order order)
    {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement())
        {
            List<Item> orderItems = order.getOrderItems();
            statement.executeUpdate("INSERT INTO orders(customerID) values('" + order.getCustomerID() + "')");
            Order currentOrder = readLatest();

            orderItems.forEach(item -> {
                String sql = String.format("INSERT INTO order_items(orderID, itemID) VALUES (%d, %d)", currentOrder.getId(), item.getId());
                try
                {
                    statement.executeUpdate(sql);
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            });
            return currentOrder;
        }
        catch (Exception e)
        {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    // Read a specific order by a given orderID - called from OrderController.update()
    public Order readOrder(Long id)
    {
        Long customerID = null;
        List<Item> orderItems = new ArrayList<>();

        String sql = "SELECT customerID FROM orders WHERE id = " + id;
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql))
        {
            resultSet.next();
            customerID = resultSet.getLong("customerID");
        }
        catch (Exception e)
        {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }

        sql = "SELECT items.* FROM order_items LEFT JOIN items ON order_items.itemID = items.id WHERE orderID = " + id;
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql))
        {
            while(resultSet.next())
            {
                orderItems.add(modelItemFromResultSet(resultSet));
            }
        }
        catch (Exception e)
        {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return new Order(id, customerID, orderItems);
    }

    @Override
    public Order update(Order order)
    {
        order.getOrderItems();
        try (Connection connection = DBUtils.getInstance().getConnection(); Statement statement = connection.createStatement())
        {
            statement.executeUpdate("DELETE FROM order_items WHERE orderID = " + order.getId());
        }
        catch (Exception e)
        {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }

        try (Connection connection = DBUtils.getInstance().getConnection(); Statement statement = connection.createStatement())
        {
            for (Item i : order.getOrderItems())
            {
                statement.executeUpdate("INSERT INTO order_items(orderID, itemID) VALUES (" + order.getId() + ", " + i.getId() + ")");
            }

        }
        catch (Exception e)
        {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }

        return null;
    }

    @Override
    public int delete(long id)
    {
        try (Connection connection = DBUtils.getInstance().getConnection(); Statement statement = connection.createStatement())
        {
            return statement.executeUpdate("DELETE FROM orders WHERE id = " + id);
        }
        catch (Exception e)
        {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public Order modelFromResultSet(ResultSet resultSet) throws SQLException
    {
        Long id = resultSet.getLong("orderID");
        Long customerID = resultSet.getLong("customerID");
        List<Item> items = new ItemDAO().readAll();

        return new Order(id, customerID, items);
    }

    public void appendToOrderMap(ResultSet resultSet) throws SQLException
    {
        Long orderID = resultSet.getLong("id");
        Long customerID = resultSet.getLong("customerID");
        orderMap.put(orderID, customerID);
    }

    public Item modelItemFromResultSet(ResultSet resultSet) throws SQLException
    {
        return itemDAO.modelFromResultSet(resultSet);
    }
}
