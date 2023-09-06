package com.example.foodapp.model.Category;

public enum Category { //TODO: Aggiungere delle categorie
    POKE("Poké"),
    FRUIT("Fruit"),
    VEGETABLES("Vegetables"),
    SALAD("Salad"),
    NUTS("Nuts"),
    SEEDS("Seeds"),
    EGGS("Eggs"),
    LEGUMES("Legumes"),
    DAIRY("Dairy"),
    FATS("Fats"),
    OILS("Oils"),
    TUBERS("Tubers"),
    YOGURT("Yogurt"),
    CEREALS("Cereal"),
    PROTEIN("Protein"),
    NONE("None"),
    UNSPECIFIED("Unspecified");

    public enum Bevarages { //TODO: Aggiungere altre bevande
        TEA("Tea"),
        COFFEE("Coffee"),
        COLA("Coca cola"),
        ORANGEADE("Orangeade");

        private final String beverageName;

        Bevarages(final String beverageName) {
            this.beverageName = beverageName;
        }

        @Override
        public String toString() {
            return this.beverageName;
        }

    }

    private final String categoryName;

    Category(final String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return this.categoryName;
    }


}
