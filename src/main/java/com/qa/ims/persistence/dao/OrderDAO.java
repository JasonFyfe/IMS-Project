package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;
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
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO orders(customerID) values('" + order.getCustomerID() + "')");
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public Order readOrder(Long id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM orders where id = " + id)) {
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Order update(Order order)
    {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("update orders set customerID ='" + order.getCustomerID() + "' where id =" + order.getId());
            return readOrder(order.getId());
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public int delete(long id)
    {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            return statement.executeUpdate("delete from orders where id = " + id);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public Order modelFromResultSet(ResultSet resultSet) throws SQLException
    {
        Long id = resultSet.getLong("id");
        Long customerID = resultSet.getLong("customerID");
        return new Order(id, customerID);
    }
}
