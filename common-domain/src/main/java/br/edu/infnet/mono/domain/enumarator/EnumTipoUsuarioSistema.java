/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.edu.infnet.mono.domain.enumarator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EnumTipoUsuarioSistema {
    OPERADOR("Operador"),
    ADMIN("Administrador/Síndico");

    private final String descricao;
    public static EnumTipoUsuarioSistema valueOfUsuariosistema(final int ordinal) {
        return EnumTipoUsuarioSistema.values()[ordinal];
    }
}
