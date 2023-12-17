package com.SOA.mailing.Exception;

public class EntityException extends Exception{
    private static  final long serialVersionUID=1L;
    public EntityException(String message)
    {
        super(message);
    }
    public static String NotFoundException(Long id)
    {
        return "The template with id "+ id +" not found.";
    }
    public static String EmptyEntityCollection() {return "This Template collection is empty.";}

    public static String InvalidTemplateType(){return "The template's type is invalid.";}

    public static String TemplateAlreadyExists(String name){
        return "Template with given name "+name+" already exists";
    }
    public static String NotFoundNameException(String name)
    {
        return "The template with name "+ name +" not found.";
    }
}
