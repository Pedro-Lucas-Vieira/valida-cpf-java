public class Cpf {

    /**
     * Realiza a validação do CPF.
     *
     * autor pedro lucas
     */
    static public boolean CPF(String strCpf) {

        // Remove tudo que não for dígito (pontos, traço, espaços etc.)
        strCpf = strCpf.replaceAll("[^0-9]", "");

        // CPF precisa ter exatamente 11 dígitos
        if (strCpf.length() != 11) {
            return false;
        }

        // Rejeita sequências com todos os dígitos iguais (111.111.111-11, etc.)
        if (strCpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int d1 = 0, d2 = 0;
        int digito1, digito2, resto;
        int digitoCPF;

        for (int nCount = 1; nCount < strCpf.length() - 1; nCount++) {
            digitoCPF = Integer.parseInt(strCpf.substring(nCount - 1, nCount));

            // multiplica a primeira casa por 10, a seguinte por 9, e assim por diante
            d1 = d1 + (11 - nCount) * digitoCPF;

            // para o segundo dígito repete o procedimento incluindo o primeiro dígito calculado
            d2 = d2 + (12 - nCount) * digitoCPF;
        }

        // Primeiro resto da divisão por 11
        resto = d1 % 11;
        digito1 = (resto < 2) ? 0 : 11 - resto;

        d2 += 2 * digito1;

        // Segundo resto da divisão por 11
        resto = d2 % 11;
        digito2 = (resto < 2) ? 0 : 11 - resto;

        // Dígito verificador informado no CPF
        String nDigVerific = strCpf.substring(strCpf.length() - 2, strCpf.length());

        // Dígitos calculados
        String nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        return nDigVerific.equals(nDigResult);
    }

    public static void main(String[] args) {
        // CPFs de teste
        System.out.println(CPF("111.444.777-35")); // true (CPF válido conhecido)
        System.out.println(CPF("11144477735"));     // true
        System.out.println(CPF("111.111.111-11"));  // false (sequência repetida)
        System.out.println(CPF("123.456.789-00"));  // false (dígito inválido)
        System.out.println(CPF(""));                 // false (tamanho inválido)
        System.out.println(CPF("123"));              // false (tamanho inválido)
    }
}
   }
   

}
