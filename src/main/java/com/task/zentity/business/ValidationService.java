package com.task.zentity.business;

public class ValidationService {

    /**
     * Checking wheter the IBAN is correct
     * @param IBAN
     * @return true if IBAN is correct, false if not
     */
    public boolean isIBANValid(String IBAN){

        int IBAN_MIN_SIZE = 15;
        int IBAN_MAX_SIZE = 34;
        long IBAN_MAX = 999999999;
        long IBAN_MODULUS = 97;
        String trimmed = IBAN.trim();

        //IBAN characters must be between 15 a 34 characters
        if(trimmed.length()<IBAN_MIN_SIZE || trimmed.length()>IBAN_MAX_SIZE){
            return false;
        }

        //Rearrange
        String reformat = trimmed.substring(4)+trimmed.substring(0,4);
        long total = 0;
        for (int i = 0;i<reformat.length();i++){
            int charValue = Character.getNumericValue(reformat.charAt(i));
            //0 - 0 , z - 35
            if(charValue<0 || charValue>35 ){
                return false;
            }
            //Compute remainder
            total = (charValue > 9 ? total * 100 : total * 10) + charValue;
            if (total > IBAN_MAX) {
                total = (total % IBAN_MODULUS);
            }
        }
        //Modulo 97 of remainder must always be 1
        return (total % IBAN_MODULUS) == 1;
    }
}
