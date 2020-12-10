package com.kapitanovslog.dailyinfoapp.services.transport.model;

import java.util.Objects;

public class Line {
    private Long id;
    private String product;
    private String line;

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Line{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", line='" + line + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line1 = (Line) o;
        return Objects.equals(id, line1.id) && Objects.equals(product, line1.product) && Objects.equals(line, line1.line);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, line);
    }
}
