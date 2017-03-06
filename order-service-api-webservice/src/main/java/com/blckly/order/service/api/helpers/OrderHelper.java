package com.blckly.order.service.api.helpers;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;

/**
 * Created by alexnikolayevsky on 3/5/17.
 */
public class OrderHelper {
    public static String generateInvoiceId() {
        String time = new SimpleDateFormat("YYMM-dd-Hms").format(System.currentTimeMillis());
        SecureRandom random = new SecureRandom();
        String rand = new BigInteger(32, random).toString(32);
        return time + '-' + rand;
    }
}
