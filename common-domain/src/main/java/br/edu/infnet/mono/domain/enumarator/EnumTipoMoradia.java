/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.mono.domain.enumarator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumTipoMoradia {
    APARTAMENTO("Apartamento"),
    CASA("Casa");
    private final String unidade;
    public static EnumTipoMoradia valueOfMoradia(final int ordinalMoradia) {
        return EnumTipoMoradia.values()[ordinalMoradia];
    }
}
