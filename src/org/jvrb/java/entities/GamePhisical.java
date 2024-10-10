package org.jvrb.java.entities;

import org.jvrb.java.enums.Platform;

import java.util.Objects;

public final class GamePhisical extends Game {

    private final double shipment;
    private final String format = "Físico";

    public GamePhisical(String title, Platform platform, double price, double shipment) {
        super(title, platform, price);
        this.shipment = shipment;
    }

    public double getShipment() {
        return shipment;
    }

    public String getFormat() {
        return format;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GamePhisical phisical)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(shipment, phisical.shipment) == 0
                && Objects.equals(format, phisical.format);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), shipment, format);
    }

    @Override
    public String toString() {
        return String.format("%s %-7s %+8.2f€ %11.2f€", super.toString(), format, shipment, calculateFinalPrice());
    }

    @Override
    public double calculateFinalPrice() {
        return super.getPrice() + shipment;
    }
}
