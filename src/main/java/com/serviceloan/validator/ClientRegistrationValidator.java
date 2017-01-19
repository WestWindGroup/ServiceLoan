package com.serviceloan.validator;

import com.serviceloan.model.Client;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * Validator for {@link Client} class, when you register,
 * extend {@link ClientValidator} class.
 *
 * @author Eugene Artemenko
 * @version 1.0
 */

@Component
public class ClientRegistrationValidator extends ClientValidator {

    @Override
    public void validate(Object ob, Errors errors) {

        Client client = (Client) ob;

        validateFirstName(client,errors);

        validateLastName(client,errors);

    }
}
