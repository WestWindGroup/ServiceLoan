package com.serviceloan.validator;


import com.serviceloan.model.Client;
import org.springframework.stereotype.Component;

/**
 * Validator for {@link Client} class, when editing,
 * extend {@link UserValidator} class.
 *
 * @author Eugene Artemenko
 * @version 1.0
 */

@Component
public class ClientEditValidator extends ClientValidator {
}
