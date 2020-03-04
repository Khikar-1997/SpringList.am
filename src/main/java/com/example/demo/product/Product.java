package com.example.demo.product;

import java.util.Objects;
import java.util.UUID;

public class Product {
    private String name;
    private double prise;
    private String type;
    private String uuid;

    public Product(String name, double prise, String type, String uuid) {
        this.name = name;
        this.prise = prise;
        if (type.equals(String.valueOf(Type.valueOf(type)))) {
            this.type = type;
        }
        this.uuid = UUID.randomUUID().toString();
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrise() {
        return prise;
    }

    public void setPrise(double prise) {
        this.prise = prise;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.prise, prise) == 0 &&
                Objects.equals(name, product.name) &&
                Objects.equals(type, product.type) &&
                Objects.equals(uuid, product.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, prise, type, uuid);
    }

    @Override
    public String toString() {
        return "product.Product{" +
                "name='" + name + '\'' +
                ", prise=" + prise +
                ", type='" + type + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
