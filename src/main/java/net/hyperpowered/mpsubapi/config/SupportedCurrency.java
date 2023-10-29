package net.hyperpowered.mpsubapi.config;

import lombok.Getter;

@Getter
public enum SupportedCurrency {

    ARS("ARS"), BRL("BRL"), CLP("CLP"), MXN("MXN"), COP("COP"), PEN("PEN"), UYU("UYU");

    private String id;

    SupportedCurrency(String id) {
        this.id = id;
    }
}
