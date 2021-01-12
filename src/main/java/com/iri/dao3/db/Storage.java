package com.iri.dao3.db;

import com.iri.dao3.model.Manufacturer;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final List<Manufacturer> manufacturers = new ArrayList<>();
    private static long manufacturerId = 0;

    public static Manufacturer addManufacturer(Manufacturer manufacturer) {
        manufacturer.setId(++manufacturerId);
        manufacturers.add(manufacturer);
        return manufacturer;
    }
}