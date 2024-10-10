package org.jvrb.java.entities;

import org.jvrb.java.enums.Platform;

import java.util.Objects;

public final class GameDigital extends Game {

    private final double discountPercent;
    private final String format = "Digital";

    public GameDigital(String title, Platform platform, double price, double discountPercent) {
        super(title, platform, price);
        this.discountPercent = discountPercent;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public String getFormat() {
        return format;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameDigital that)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(discountPercent, that.discountPercent) == 0
                && Objects.equals(format, that.format);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discountPercent, format);
    }

    @Override
    public String toString() {
        return String.format("%s %-7s %3s%2.2f%% %11.2f€", super.toString(), format, "-", discountPercent, calculateFinalPrice());
    }

    @Override
    public double calculateFinalPrice() {
        return super.getPrice() - (discountPercent * super.getPrice() / 100);
    }
}
