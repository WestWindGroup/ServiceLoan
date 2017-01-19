package com.serviceloan.validator;


import com.serviceloan.model.Client;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * Validator for {@link Client} class, when editing,
 * extend {@link ClientValidator} class.
 *
 * @author Eugene Artemenko
 * @version 1.0
 */

@Component
public class ClientEditValidator extends ClientValidator {


    @Override
    public void validate(Object ob, Errors errors) {

        Client client = (Client) ob;
        Client clientOld = clientService.getById(client.getId());

        if(!client.getFirstName().equals(clientOld.getFirstName())){
            validateFirstName(client,errors);
        }

        if(!client.getLastName().equals(clientOld.getLastName())){
            validateLastName(client,errors);
        }

    }
}
