package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;
import com.qa.ims.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements Dao<Order>
{
    public static final Logger LOGGER = LogManager.getLogger();
    private Utils utils = new Utils();

    // todo Fix SQL query to combine data from required tables
    @Override
    public List<Order> readAll()
    {
        try (Connection connection = DBUtils.getInstance().getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("select * from orders"))
        {
            List<Order> orders = new ArrayList<>();
            while (resultSet.next())
            {
                orders.add(modelFromResultSet(resultSet));
            }
            return orders;
        }
        catch (SQLException e)
        {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Order readLatest()
    {
        try (Connection connection = DBUtils.getInstance().getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1"))
        {
            resultSet.next();
            return modelFromResultSet(resultSet);
        }
        catch (Exception e)
        {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Order create(Order order)
    {
        try (Connection connection = DBUtils.getInstance().getConnection(); Statement statement = connection.createStatement())
        {
            statement.executeUpdate("INSERT INTO orders(customerID) values('" + order.getCustomerID() + "')");
            Order currentOrder = readLatest();

            boolean addNewItem = false;
            do
            {
                LOGGER.info("Please enter the item ID to add to the order");
                Long itemID = utils.getLong();
                LOGGER.info("How many of these are being added to the order");
                int itemQuantity = Integer.parseInt(utils.getString());

                String orderItems = String.format("INSERT INTO order_items(itemID, orderID, quantity) VALUES (%d, %d, %d)", itemID, currentOrder.getId(), itemQuantity);
                statement.executeUpdate(orderItems);

                LOGGER.info("Add another item?");
                LOGGER.info("Yes or No");
                String choice = utils.getString();

                addNewItem = choice.toLowerCase().equals("yes");
            }
            while (addNewItem);

            return currentOrder;
        }
        catch (Exception e)
        {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public Order readOrder(Long id)
    {
        // todo refactor readOrder()
        // join of tables
        // select id and customerID from orders
        // select itemID and quantity from order_items where orderID
        // for each item select price from items where itemID
        try (Connection connection = DBUtils.getInstance().getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT * FROM orders where id = " + id))
        {
            resultSet.next();
            return modelFromResultSet(resultSet);
        }
        catch (Exception e)
        {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Order update(Order order)
    {

        // todo refactor OrderDAO.update
        try (Connection connection = DBUtils.getInstance().getConnection(); Statement statement = connection.createStatement())
        {
            statement.executeUpdate("update orders set customerID ='" + order.getCustomerID() + "' where id =" + order.getId());
            return readOrder(order.getId());
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
        // todo refactor OrderDAO to recursively delete data from order_items table
        try (Connection connection = DBUtils.getInstance().getConnection(); Statement statement = connection.createStatement())
        {
            return statement.executeUpdate("delete from orders where id = " + id);
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
        // todo refactor OrderDAO model from SQL
        Long id = resultSet.getLong("id");
        Long customerID = resultSet.getLong("customerID");
        return new Order(id, customerID);
    }
}
