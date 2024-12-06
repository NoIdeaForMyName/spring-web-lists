package com.example.productlist;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class ProductService extends ArrayList<Product> {

    private final Set<Long> ids;

    public ProductService() {
        super();
        ids = new HashSet<>();
    }

    @Override
    public boolean add(Product product) {
        if (ids.contains(product.getId())) {
            return false;
        }
        ids.add(product.getId());
        super.add(product);
        return true;
    }

    public Set<Long> getUsedIDs() {
        return ids;
    }

    public Product getById(long id) {
        return this.get(getIndexById(id));
    }

    public Integer getIndexById(long id) {
        for (int i=0; i < this.size(); i++) {
            if (this.get(i).getId() == id) {
                return i;
            }
        }
        return null;
    }

}
