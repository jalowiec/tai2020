package com.edu.agh.tai.quotesservice;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class QuotesServiceController {

    @Autowired
    private QuoteMapper quoteMapper;


    //TODO sprawdzenie konwencji

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/quotes")
    public QuoteDto getQuote(){
        return quoteMapper.quoteToQuoteDto(getExternalQuote());
    }

    private Quote getExternalQuote(){
        final String uri = "https://opinionated-quotes-api.gigalixirapp.com/v1/quotes";
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

        return new Quote(quote, author);
    }


}
