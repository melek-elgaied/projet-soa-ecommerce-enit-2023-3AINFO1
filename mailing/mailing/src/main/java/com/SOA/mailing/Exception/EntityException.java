package com.SOA.mailing.Exception;

public class EntityException extends Exception{
    private static  final long serialVersionUID=1L;
    public EntityException(String message)
    {
        super(message);
    }
    public static String NotFoundException(Long id)
    {
        return "Entity with "+ id +" not found.";
    }
    public static String EmptyEntityCollection() {return "This Entity collection is empty.";}

    public static String InvalidTemplateType(){return "The template's type is invalid.";}

}
