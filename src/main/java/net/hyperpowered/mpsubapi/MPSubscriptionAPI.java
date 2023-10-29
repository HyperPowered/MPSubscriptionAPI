package net.hyperpowered.mpsubapi;

import lombok.SneakyThrows;
import net.hyperpowered.mpsubapi.config.RecurringConfig;
import net.hyperpowered.mpsubapi.model.Plan;
import net.hyperpowered.mpsubapi.model.Subscription;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MPSubscriptionAPI {

    private String accessToken;

    public MPSubscriptionAPI(String accessToken) {
        this.accessToken = accessToken;
    }

    private Map<String, String> getHeader(boolean allArgs){
        Map<String, String> h = new HashMap<>();
        h.put("Authorization", "Bearer "+accessToken);
        if(allArgs) {
            h.put("Content-Type", "application/json");
        }
        return h;
    }

    @SneakyThrows
    public Plan createAndConfigurePlan(Plan plan){
        Connection.Response response = Jsoup.connect("https://api.mercadopago.com/preapproval_plan")
                .headers(getHeader(true))
                .requestBody(plan.toJSON().toString())
                .method(Connection.Method.POST).execute();
        JSONObject payload = new JSONObject(response.body());
        plan.setId(payload.getString("id"));
        plan.setAppId(payload.getLong("application_id"));
        plan.setCollectorId(payload.getLong("collector_id"));
        plan.setExternalReference(payload.getLong("external_reference"));
        plan.setInitPoint(payload.getString("init_point"));
        plan.setStatus(payload.getString("status"));
        return plan;
    }

    @SneakyThrows
    public List<Subscription> search(String email){
        List<Subscription> subscriptions = new ArrayList<>();
        Connection.Response response = Jsoup.connect("https://api.mercadopago.com/preapproval/search?payer_email="+email)
                .headers(getHeader(false))
                .method(Connection.Method.GET).execute();

        JSONObject payload = new JSONObject(response.body());
        for (Object results : payload.getJSONArray("results")) {
            JSONObject ps = (JSONObject) results;
            Subscription sub = new Subscription();
            sub.fromJSON(ps, email);
            subscriptions.add(sub);
        }

        return subscriptions;
    }

    @SneakyThrows
    public Subscription getSubscription(String id){
        Connection.Response response = Jsoup.connect("https://api.mercadopago.com/preapproval/"+id)
                .headers(getHeader(false))
                .method(Connection.Method.GET).execute();

        Subscription subscription = new Subscription();
        subscription.fromJSON(new JSONObject(response.body()), null);
        return subscription;
    }
}
