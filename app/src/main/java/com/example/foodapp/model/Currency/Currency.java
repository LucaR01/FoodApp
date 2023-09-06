package com.example.foodapp.model.Currency;

public enum Currency {

    EURO("€"),
    USD("$");

    private final String currency;

    Currency(final String currency) {
        this.currency = currency;
    }

    //TODO: getCurrency e toString sono uguali, remove one of the two!

    public final String getCurrency() {
        return this.currency;
    }

    @Override
    public String toString() {
        return this.currency;
    }

    public static final Currency DEFAULT_CURRENCY = Currency.USD;

}
