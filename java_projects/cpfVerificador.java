public class cpfVerificador {
    public static String sanitizer (String input) {
        String resultado = null;
        StringBuilder sanitizedInput = new StringBuilder();
            if (!input.equals(null)) {
                char caracter;
                for (int i = 0; i < input.length(); i++) {
                    caracter = input.charAt(i);
                    switch (caracter) {
                        case '0': case '1': case '2': case '3': case '4':
                        case '5': case '6': case '7': case '8': case '9':
                            sanitizedInput.append(caracter);
                            break;
                    }
                }
                resultado = sanitizedInput.toString();
            }
        return resultado;
    }

    public static boolean verificador(String cpf) {
        boolean resultado = false;

        String newCpf = sanitizer(cpf);
        if (newCpf != null) {
            if (newCpf.length() == 11) {
                if (newCpf.chars().distinct().count() == 1) {
                    resultado = false;
                } else {
                    int peso = 10, soma = 0, digVerfX, digVerfY;
                    String auxiliar;

                    for (int i = 0; i < 9; i++) {
                        auxiliar = newCpf.substring(i, i + 1);
                        soma += Integer.parseInt(auxiliar, 10) * peso;
                        peso -= 1;
                    }
                    if (soma % 11 < 2) {
                        digVerfX = 0;
                    } else {
                        digVerfX = 11 - (soma % 11);
                    }

                    peso = 11;
                    soma = 0;
                    auxiliar = "";
                    for (int i = 0; i < 10; i++) {
                        auxiliar = newCpf.substring(i, i + 1);
                        soma += Integer.parseInt(auxiliar, 10) * peso;
                        peso -= 1;
                    }
                    if (soma % 11 < 2) {
                        digVerfY = 0;
                    } else {
                        digVerfY = 11 - (soma % 11);
                    }

                    if (digVerfX == Integer.parseInt(newCpf.substring(9, 10)) && digVerfY == Integer.parseInt(newCpf.substring(10, 11))) {
                        resultado = true;
                    }
                }
            }
        }
        return resultado;
    }
}
