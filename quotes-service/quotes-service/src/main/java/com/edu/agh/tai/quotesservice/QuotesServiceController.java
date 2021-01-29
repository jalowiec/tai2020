package com.edu.agh.tai.quotesservice;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/users/{id}")
@CrossOrigin(origins = "*")
public class QuotesServiceController {

    @Autowired
    private QuoteMapper quoteMapper;



    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(method = RequestMethod.GET, value = "/quotes/quote", produces= MediaType.APPLICATION_JSON_VALUE)
    @HystrixCommand(fallbackMethod = "getDefaultQuote")
    public QuoteDto getQuote(@PathVariable(value = "id") int pathUserId){

        final String uri = "http://localhost:8080/users/" + pathUserId +"/hobby";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        JsonArray jsonArray = JsonParser.parseString(result).getAsJsonArray();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<jsonArray.size()-1; i++){
            stringBuilder.append(jsonArray.get(i).getAsJsonObject().get("name").getAsString());
            stringBuilder.append(",");
        }
        stringBuilder.append(jsonArray.get(jsonArray.size()-1).getAsJsonObject().get("name").getAsString());

        return quoteMapper.quoteToQuoteDto(getExternalQuote(stringBuilder.toString()));
    }

    private Quote getExternalQuote(String hobbies){
        final String uri = "https://opinionated-quotes-api.gigalixirapp.com/v1/quotes?tags=" + hobbies ;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();
        JsonObject jsonQuoteObject = jsonObject.getAsJsonArray("quotes").get(0).getAsJsonObject();
        String quote = jsonQuoteObject.get("quote").getAsString();
        String author = "Unknown";
        if(jsonQuoteObject.get("author") != null){
            author = jsonQuoteObject.get("author").getAsString();
        }

        logger.info("{}", author + ":" + quote.substring(0, 5));

        return new Quote(quote, author, hobbies);
    }

    private QuoteDto getDefaultQuote(int id){

        logger.info("{}", "default author" + ":" + "default quote");

        return new QuoteDto("Always look on the bright side of life", "Unknown", "Unknown");
    }



}
