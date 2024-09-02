package br.com.w4solution.alphacontrol.model.financeiro;

public enum TipoEntrada {
    OFERTA("Oferta"),
    DIZIMO("Dizimo"),
    VOTO("Voto"),
    DOACAO("Doacao");

    private String tipoEntradaString;

    TipoEntrada(String tipoEntradaString){
        this.tipoEntradaString = tipoEntradaString;
    }

    public static TipoEntrada fromString(String text) {
        for (TipoEntrada tipoEntrada : TipoEntrada.values()) {
            if (tipoEntrada.tipoEntradaString.equalsIgnoreCase(text)) {
                return tipoEntrada;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
