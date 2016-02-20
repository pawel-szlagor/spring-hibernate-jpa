package pl.spring.demo.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import pl.spring.demo.to.CustomerTo;

/**
 * Created by Paweł on 25.01.2016.
 */
public class CustomerValidator implements Validator {

    private static final int[] weightsForEachDigitInNIP = {6,5,7,2,3,4,5,6,7};
    private static final int[] weightsForEachDigitInREGONShort = {8,9,2,3,4,5,6,7};
    private static final int[] weightsForEachDigitInREGONLong = {2,4,8,5,0,9,7,3,6,1,2,4,8};
    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerTo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "NIP", "NIPBlank","model.to.customer.blank_nip");
        CustomerTo customerTo = (CustomerTo) target;
        validateNIP(customerTo.getNIP(), errors);
        validateREGON(customerTo.getREGON(), errors);
    }

    private void validateNIP(String nip, Errors errors) {
        if(nip.matches("\\d{9}"))
            errors.rejectValue("NIP", "NIPformatIncorrect", "model.to.customer.NIPFormatIncorrect");
        else if(!checkControlSum(nip, weightsForEachDigitInNIP))
            errors.rejectValue("NIP", "NIPformatIncorrect", "model.to.customer.NIPFormatIncorrect");
    }

    private void validateREGON(String regon, Errors errors) {

    }

    private boolean checkControlSum(String field, int[] weights){
        char [] digits = field.toCharArray();
        int sum=0;
        for(int i = 0; i<field.length()-1; i++){
            sum+=Integer.parseInt(""+field.charAt(i));
        }
        return field.endsWith(sum+"");
    }
}
