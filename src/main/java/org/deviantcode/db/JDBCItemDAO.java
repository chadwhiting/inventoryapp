package org.deviantcode.db;

import org.deviantcode.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class JDBCItemDAO implements ItemDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM INVENTORY");
        for (Map row : rows) {
            Item item = new Item();
            item.setName((String)row.get("name"));
            item.setDescription((String)row.get("description"));
            item.setManufacturer((String)row.get("manufacturer"));
            item.setMake((String)row.get("make"));
            item.setModel((String)row.get("model"));
            item.setColor((String)row.get("color"));
            item.setSerialNo((String)row.get("serialno"));
            item.setPrice((BigDecimal) row.get("price"));
            item.setCreated((Date)row.get("created"));
            item.setLastUpdated((Date)row.get("lastupdated"));
            items.add(item);
        }
        return items;
    }

    public Item getItemByName(String name) {
        return jdbcTemplate.queryForObject("SELECT * FROM INVENTORY WHERE NAME = ?",
                new Object[] { name }, new BeanPropertyRowMapper<>(Item.class));
    }
}
