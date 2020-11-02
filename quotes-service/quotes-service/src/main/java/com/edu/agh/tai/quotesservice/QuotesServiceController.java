package com.edu.agh.tai.quotesservice;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class QuotesServiceController {

    @Autowired
    private QuoteMapper quoteMapper;


    //TODO sprawdzenie konwencji

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/quotes")
    @HystrixCommand(fallbackMethod = "getDefaultQuote")
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

    private QuoteDto getDefaultQuote(){

        logger.info("{}", "default author" + ":" + "default quote");

        return new QuoteDto("Always look on the bright side of life", "Unknown");
    }



}
