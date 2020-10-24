package com.edu.agh.tai.quotesservice.controller;

import com.edu.agh.tai.quotesservice.model.Quote;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuotesServiceController {
    private Quote quote = new Quote("[...] consciousness is often said to be ill-defined. Yet if physicalistic idealism is true, then we already possess the mathematical apparatus of a theory of consciousness. All that’s hard is to “read off” the textures of experience from the solutions to the equations.", "en", "David Pearce");

    //TODO sprawdzenie konwencji
    //TODO dodanie mappera

    @GetMapping("/quote")
    public Quote getQuote(){

        return quote;

    }


}
