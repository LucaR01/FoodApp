package com.example.foodapp.model.Currency;

public enum Currency {

    EURO("€"),
    USD("$");

    private final String currency;

    Currency(final String currency) {
        this.currency = currency;
    }

    public final String getCurrency() {
        return this.currency;
    }

    @Override
    public String toString() {
        return this.currency;
    }

}
