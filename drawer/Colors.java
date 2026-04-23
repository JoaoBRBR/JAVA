public enum Colors {
    // 1. Definição dos valores (Isso chama o construtor abaixo)
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    BRIGHT_RED("\u001B[91m"),
    BRIGHT_GREEN("\u001B[92m"),
    RESET("\u001B[0m");

    // 2. Onde o código ANSI será guardado
    private final String code;

    // 3. O CONSTRUTOR (O que estava faltando!)
    // Ele deve ter o mesmo nome do enum e receber o tipo que você passou lá em cima
    Colors(String code) {
        this.code = code;
    }

    // 4. Método para a sua biblioteca usar internamente
    public String getCode() {
        return this.code;
    }
}