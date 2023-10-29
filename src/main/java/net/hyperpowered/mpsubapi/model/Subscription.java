package net.hyperpowered.mpsubapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {

    private String id;
    private String planId;
    private String payerEmail;
    private Long payerId;
    private String paymentMethod;
    private LocalDate createdDate;
    private LocalDate lastModifiedDate;
    private LocalDate nextPaymentDate;
    private String status;

    public void fromJSON(JSONObject ps, String email) {
        this.id = ps.getString("id");
        this.planId = ps.getString("preapproval_plan_id");
        this.paymentMethod = ps.getString("payment_method_id");
        this.payerEmail = email;
        this.payerId = ps.getLong("payer_id");
        this.createdDate = LocalDate.parse(ps.getString("date_created"));
        this.lastModifiedDate = LocalDate.parse(ps.getString("last_modified"));
        this.nextPaymentDate = LocalDate.parse(ps.getString("next_payment_date"));
        this.status = ps.getString("status");
    }
}
