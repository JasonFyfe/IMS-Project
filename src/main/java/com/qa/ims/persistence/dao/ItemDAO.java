package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO implements Dao<Item>
{
    public static final Logger LOGGER = LogManager.getLogger();

    /**
     * Reads all items from the database
     */
    @Override
    public List<Item> readAll()
    {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from items");)
        {
            List<Item> items = new ArrayList<>();
            while (resultSet.next())
            {
                items.add(modelFromResultSet(resultSet));
            }
            return items;
        }
        catch (SQLException e)
        {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Returns the newest item added to the database.
     */
    public Item readLatest()
    {
        try (Connection connection = DBUtils.getInstance().getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY id DESC LIMIT 1"))
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

    /**
     * Creates an item in the database
     */
    @Override
    public Item create(Item item)
    {
        try (Connection connection = DBUtils.getInstance().getConnection(); Statement statement = connection.createStatement())
        {
            statement.executeUpdate("INSERT INTO items(name, price) values('" + item.getName() + "','" + item.getPrice() + "')");
            return readLatest();
        }
        catch (Exception e)
        {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Retrieve an item from the Database by a specific id
     */
    public Item readItem(Long id)
    {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM items where id = " + id))
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

    /**
     * Updates an item in the database
     */
    @Override
    public Item update(Item item)
    {
        try (Connection connection = DBUtils.getInstance().getConnection(); Statement statement = connection.createStatement())
        {
            statement.executeUpdate("update items set name ='" + item.getName() + "', price ='" + item.getPrice() + "' where id =" + item.getId());
            return readItem(item.getId());
        }
        catch (Exception e)
        {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     * Deletes an item in the database
     */
    @Override
    public int delete(long id)
    {
        try (Connection connection = DBUtils.getInstance().getConnection(); Statement statement = connection.createStatement();)
        {
            return statement.executeUpdate("delete from items where id = " + id);
        }
        catch (Exception e)
        {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public Item modelFromResultSet(ResultSet resultSet) throws SQLException
    {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        Double price = resultSet.getDouble("price");
        return new Item(id, name, price);
    }

    public List<Item> readItemsByOrder(Long id)
    {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT items.* FROM order_items LEFT JOIN items ON order_items.itemID = items.id WHERE order_items.orderID = " + id;
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql))
        {
            resultSet.next();
            items.add(modelFromResultSet(resultSet));
        }
        catch (Exception e)
        {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return items;
    }
}
