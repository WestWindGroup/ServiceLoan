package com.serviceloan.validator;

import com.serviceloan.model.Client;
import com.serviceloan.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

/**
 * Validator for {@link Client} class,
 * add functionality in {@link AbstractValidator} class.
 *
 * @author Eugene Artemenko
 * @version 1.0
 */
public abstract class ClientValidator extends AbstractValidator{

    @Autowired
    protected ClientService clientService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Client.class.equals(aClass);
    }


    protected void validateFirstName(Client client, Errors errors){

        validateField("firstName",errors);

        if(!checkLastAndFirstNameWithRegExp(client.getFirstName())){
            client.setFirstName("");
            errors.rejectValue("firstName", "key.client.firstname", null, null);
        }
    }

    protected void validateLastName(Client client, Errors errors){

        validateField("lastName",errors);

        if(!checkLastAndFirstNameWithRegExp(client.getLastName())){
            client.setLastName("");
            errors.rejectValue("lastName", "key.client.lastname", null, null);
        }
    }

}
