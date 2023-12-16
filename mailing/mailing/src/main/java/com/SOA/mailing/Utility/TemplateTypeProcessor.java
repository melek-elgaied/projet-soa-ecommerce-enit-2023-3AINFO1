package com.SOA.mailing.Utility;

import com.SOA.mailing.Enum.TemplateType;
import com.SOA.mailing.Exception.EntityException;

public class TemplateTypeProcessor {
    public static TemplateType processTemplateType(String userInput) throws EntityException {
        try {
            return TemplateType.valueOf(userInput.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new EntityException(EntityException.InvalidTemplateType());
        }
    }
}
