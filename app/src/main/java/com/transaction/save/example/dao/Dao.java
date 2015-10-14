package com.transaction.save.example.dao;

import com.transaction.save.example.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class Dao {

    // no tests for dao

    private Map<Long, Transaction> db = new HashMap<>();
    private Map<String, List<Long>> typeMap = new HashMap<>();

    public Transaction get(final long id) {
        return db.get(id);
    }

    public boolean containsTransaction(final long id){
        return db.containsKey(id);
    }

    public List<Long> getIds(final String type) {
        return typeMap.getOrDefault(type, new ArrayList<>());
    }

    public void put(final long id, final Transaction transaction) {
        db.put(id, transaction);
        mapType(id, transaction);
    }

    private void mapType(final long id, final Transaction transaction) {
        List<Long> ids = typeMap.getOrDefault(id, new ArrayList<>());
        ids.add(id);
        typeMap.put(transaction.type, ids);
    }
}
