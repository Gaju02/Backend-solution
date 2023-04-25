package projektNBP.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Service
public class NBPService {
    public String getCurrencyDate(@PathVariable String code, @PathVariable String date) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.nbp.pl/api/exchangerates/rates/A/"+code+"/"+date;
        String response = restTemplate.getForObject(url, String.class);
        int midIndex = response.indexOf("\"mid\":") + 6;
        int endIndex = response.indexOf("}", midIndex);
        String midValue = response.substring(midIndex, endIndex).trim();
        return "Average exchange rate: "+midValue;
    }
    public String getQuotes(@PathVariable String code, @PathVariable String quotationNumber) throws JsonProcessingException {
        String statement;
        if(Integer.parseInt(quotationNumber)>255 || Integer.parseInt(quotationNumber)<=0){
            statement = "quotationNumber should be: N<=255 and N>0";
        }
        else {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://api.nbp.pl/api/exchangerates/rates/a/" + code + "/last/" + quotationNumber;
            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode ratesNode = rootNode.path("rates");
            double minMid = Double.MAX_VALUE;
            double maxMid = Double.MIN_VALUE;
            for (JsonNode rateNode : ratesNode) {
                double mid = rateNode.path("mid").asDouble();
                if (mid < minMid) {
                    minMid = mid;
                }
                if (mid > maxMid) {
                    maxMid = mid;
                }
            }
            statement = "Max:"+maxMid+", Min:"+minMid;
        }
        return statement;
    }
    public String getDifference(@PathVariable String code, @PathVariable String quotationNumber) throws JsonProcessingException {
        String statement;
        if(Integer.parseInt(quotationNumber)>255 || Integer.parseInt(quotationNumber)<=0){
            statement = "quotationNumber should be: N<=255 and N>0";
        }
        else {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://api.nbp.pl/api/exchangerates/rates/c/" + code + "/last/" + quotationNumber;
            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response);
            JsonNode ratesNode = rootNode.get("rates");
            double maxAsk = 0.0;
            double minBid = Double.MAX_VALUE;
            for (JsonNode rateNode : ratesNode) {
                double ask = rateNode.get("ask").asDouble();
                double bid = rateNode.get("bid").asDouble();
                if (ask > maxAsk) {
                    maxAsk = ask;
                }
                if (bid < minBid) {
                    minBid = bid;
                }
            }
            statement= String.valueOf(maxAsk - minBid);
            statement = "The major difference between the buy and ask rate:"+statement;
        }
        return statement;
    }
}
