package projektNBP.demo.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import projektNBP.demo.service.NBPService;

/*
When we've got Lombok, we can just type @RequiredArgsConstructor, so we don't have to
create a constructor to specify a Dependency Injection.
 */
@RestController
@RequestMapping(path = "api/v1")
public class NBPController {

    private final NBPService nbpService;

    NBPController(NBPService nbpService){
        this.nbpService = nbpService;
    }

    @GetMapping("/currency/{code}/{date}")
    public String getCurrencyDate(@PathVariable String code, @PathVariable String date) {
        return nbpService.getCurrencyDate(code,date);
    }

    @GetMapping("/quotes/{code}/{quotationNumber}")
    public String getQuotes(@PathVariable String code, @PathVariable String quotationNumber) throws JsonProcessingException {
        return nbpService.getQuotes(code,quotationNumber);
    }
    @GetMapping("/difference/{code}/{quotationNumber}")
    public String getDifference(@PathVariable String code, @PathVariable String quotationNumber) throws JsonProcessingException {
        return nbpService.getDifference(code,quotationNumber);
    }
}
