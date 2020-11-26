package com.qa.ims.persistence.domain;


public class Item
{
    private Long id;
    private String name;
    private Double price;

    public Item(String name, Double price)
    {
        this.name = name;
        this.price = price;
    }

    public Item(Long id, String name, Double price)
    {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    @Override
    public String toString()
    {
        return "Item ID: " + id + " | Item Name:" + name + " | Price:" + price;
    }

    @Override
    public boolean equals(Object obj)
    {
        Item other = (Item) obj;

        if (this == obj) { return true; }
        if (obj == null) { return false; }
        if (getClass() != obj.getClass()) { return false; }

        if (name == null)
        {
            if (other.name != null) { return false; }
        }
        else if (!name.equals(other.name)) { return false; }

        if (id == null)
        {
            if (other.id != null) { return false; }
        }
        else if (!id.equals(other.id)) { return false; }

        if (price == null)
        {
            if (other.price != null) { return false; }
        }
        else if (!price.equals(other.price)) { return false; }

        return true;
    }
}
