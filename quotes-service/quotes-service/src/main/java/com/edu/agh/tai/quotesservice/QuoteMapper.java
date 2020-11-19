package com.edu.agh.tai.quotesservice;

import org.springframework.stereotype.Component;

@Component
public class QuoteMapper {

    public QuoteDto quoteToQuoteDto(final Quote quote){
        return new QuoteDto(quote.getContent(), quote.getAuthor());
    }





}
