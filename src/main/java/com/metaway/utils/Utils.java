package com.metaway.utils;

public class Utils {

    public static String removeMascaraCpf(String cpf) {
        return ((cpf != null && !cpf.isEmpty()) ? cpf.replace(".", "").replace("-", "") : "");
    }

    public static String colocarMascaraNoCpf(String cpf) {
        return cpf.substring(0,3).concat(".").concat(cpf.substring(3,6).concat(".")
        .concat(cpf.substring(6,9)).concat("-").concat(cpf.substring(9,11)));
    }

}
