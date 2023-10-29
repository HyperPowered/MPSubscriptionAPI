package net.hyperpowered.mpsubapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.hyperpowered.mpsubapi.config.PaymentType;
import net.hyperpowered.mpsubapi.config.RecurringConfig;
import net.hyperpowered.mpsubapi.config.SupportedCurrency;
import org.json.JSONObject;

import java.net.URL;

@Data
@AllArgsConstructor
public class Plan {

    private String id;
    private Long appId;
    private Long collectorId;
    private Long externalReference;
    private String initPoint;
    private String status;
    private RecurringConfig recurring;
    private URL backUrl;
    private PaymentType supportPayment;
    private String description;
    private int amount;
    private SupportedCurrency currency;

    public Plan(RecurringConfig recurring, URL backUrl, PaymentType supportPayment, String description, int amount, SupportedCurrency currency) {
        this.recurring = recurring;
        this.backUrl = backUrl;
        this.supportPayment = supportPayment;
        this.description = description;
        this.amount = amount;
        this.currency = currency;
    }

    public JSONObject toJSON(){
        JSONObject pp = new JSONObject();
        pp.put("reason", description)
                .put("back_url", backUrl.toString())
                .put("payment_methods_allowed", supportPayment.toJSON())
                .put("auto_recurring", recurring.toJSON(amount, currency.getId()));
        return pp;
    }
}
