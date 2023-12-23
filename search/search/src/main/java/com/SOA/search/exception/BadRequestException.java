package com.SOA.search.exception;

import java.io.IOException;

/*
 * Aziz lachtar keeps changing methods signatures without any prior conventions or agreements, based pn that the codded added within the same commit as this comment, as well as many others following this one, is just making sure methods signature are similar to the ones agreed on.
 */
public class BadRequestException extends ExceptionSoa{
    public BadRequestException(String message, IOException e) {
        super(message);
    }

    public BadRequestException(String message) {
        super(message);
    }
}
