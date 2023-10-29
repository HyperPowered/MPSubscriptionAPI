package net.hyperpowered.mpsubapi.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
public enum PaymentType {

    ALL(),
    CREDIT_CARD(Collections.singletonList("credit_card"), "Visa", "master", "amex", "elo", "hipercard"),
    TICKET(Collections.singletonList("ticket"), "pec", "bolbradesco"),
    BANK_TRANSFER(Collections.singletonList("bank_transfer"), "pix");

    private List<String> methods;
    private List<String> types;

    PaymentType(){
        for (PaymentType value : values()) {
            this.methods.addAll(value.methods);
            this.types.addAll(value.types);
        }
    }

    PaymentType(List<String> methods, String... type){
        this.methods = methods;
        this.types = Arrays.asList(type);
    }

    public JSONObject toJSON(){
        JSONObject pm = new JSONObject();
        pm.put("payment_types", new JSONArray(types));
        pm.put("payment_methods", new JSONArray(methods));
        return pm;
    }
}
