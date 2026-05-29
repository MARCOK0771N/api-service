package com.eglobal.api_service.util;

import java.security.SecureRandom;

public class ReferenceGenerator {
    private ReferenceGenerator() {}

    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generarReferencia() {
        int numero = 100000 + secureRandom.nextInt(900000);
        return String.valueOf(numero);
    }
}
