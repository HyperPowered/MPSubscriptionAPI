package net.hyperpowered.mpsubapi.config;

import lombok.Builder;
import lombok.Data;
import org.json.JSONObject;

@Data
@Builder
public class RecurringConfig {

    private int frequency = 1;
    private String frequencyType = "months";
    private int repetitions = -1;
    private int billingDay = -1;
    public JSONObject toJSON(int amount, String currency){
        JSONObject pj = new JSONObject();
        pj.put("frequency", frequency)
                .put("frequency_type", frequencyType)
                .put("transaction_amount", amount)
                .put("currency_id", currency);

        if(repetitions > 0){
            pj.put("repetitions", repetitions);
        }
        if(billingDay > 0){
            pj.put("billing_day", billingDay);
        }
        return pj;
    }
}
