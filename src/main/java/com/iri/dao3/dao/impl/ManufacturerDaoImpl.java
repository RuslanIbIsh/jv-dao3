package com.iri.dao3.dao.impl;

import com.iri.dao3.dao.ManufacturerDao;
import com.iri.dao3.db.Storage;
import com.iri.dao3.lib.Dao;
import com.iri.dao3.model.Manufacturer;
import java.util.List;
import java.util.Optional;

@Dao
public class ManufacturerDaoImpl implements ManufacturerDao {
    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        return Storage.addManufacturer(manufacturer);
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        return Storage.manufacturers.stream()
                .filter(manufacturer -> manufacturer.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Manufacturer> getAll() {
        return Storage.manufacturers;
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        return Storage.manufacturers.stream()
                .filter(manufacturer1 -> manufacturer1.getId().equals(manufacturer.getId()))
                .map(manufacturer1 -> manufacturer1 = manufacturer)
                .findFirst()
                .get();
    }

    @Override
    public boolean delete(Long id) {
        return Storage.manufacturers.removeIf(manufacturer -> manufacturer.getId().equals(id));
    }
}
