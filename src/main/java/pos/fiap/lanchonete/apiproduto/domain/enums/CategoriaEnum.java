package pos.fiap.lanchonete.apiproduto.domain.enums;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public enum CategoriaEnum {

    LANCHE("lanche"),
    ACOMPANHAMENTO("acompanhamento"),
    BEBIDA("bebida"),
    SOBREMESA("sobremesa");

    private final String label;

    public static Optional<CategoriaEnum> getCategoriaByLabel(String label) {
        try {
            return Optional.of(CategoriaEnum.valueOf(label.toUpperCase()));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
