package com.serviceloan.validator;

import com.serviceloan.model.Client;
import org.springframework.validation.Errors;

public class ClientRegistrationValidator extends ClientValidator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Client.class.equals(aClass);
    }

    @Override
    public void validate(Object ob, Errors errors) {

        Client client = (Client) ob;

        validateField("firstName",errors);

        validateField("lastName",errors);

        validateFirstName(client,errors);

        validateLastName(client,errors);

    }
}
