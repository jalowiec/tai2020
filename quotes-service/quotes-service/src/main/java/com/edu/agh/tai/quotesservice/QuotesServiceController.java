package com.edu.agh.tai.quotesservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuotesServiceController {

    @Autowired
    private QuoteMapper quoteMapper;


    //TODO sprawdzenie konwencji


    @GetMapping("/quote")
    public QuoteDto getQuote(){
        List<Tag> tagList = new ArrayList<>();
        tagList.add(new Tag("future"));
        tagList.add(new Tag("evolution"));
        tagList.add(new Tag("ethics"));

        Quote quote = new Quote("[...] consciousness is often said to be ill-defined. Yet if physicalistic idealism is true, then we already possess the mathematical apparatus of a theory of consciousness. All that’s hard is to “read off” the textures of experience from the solutions to the equations.", "en", "David Pearce", tagList);

        return quoteMapper.quoteToQuoteDto(quote);

    }


}
