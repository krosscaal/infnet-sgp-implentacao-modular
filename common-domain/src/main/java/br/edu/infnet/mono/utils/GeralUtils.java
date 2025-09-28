/*
 * Author: Krossby Adhemar Camacho Alviz
 * owned by Krossft.
 */

package br.edu.infnet.mono.utils;

import br.edu.infnet.mono.domain.dto.UsuarioDTO;
import br.edu.infnet.mono.domain.enumarator.EnumTipoResidente;
import br.edu.infnet.mono.domain.enumarator.EnumTipoSituacao;
import br.edu.infnet.mono.domain.enumarator.EnumTipoUsuarioSistema;

import java.util.regex.Pattern;

public class GeralUtils {
    private static final Pattern SOMENTE_NUMEROS = Pattern.compile("^\\d+$");
    private static final Pattern SOMENTE_LETRAS = Pattern.compile("^[\\p{L}\\s]+$");

    private GeralUtils() {}

    public static boolean contemNumeros(String campo) {
        if (campo == null || campo.trim().isEmpty()) {
            return false;
        }
        return SOMENTE_NUMEROS.matcher(campo).matches();
    }
    public static boolean contemLetras(String campo) {
        if (campo == null || campo.trim().isEmpty()) {
            return false;
        }
        return SOMENTE_LETRAS.matcher(campo).matches();
    }

    public static UsuarioDTO criarUsuario(String[] campos) {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setNome(campos[0]);
        usuario.setSobreNome(campos[1]);
        usuario.setCpf(!campos[2].equalsIgnoreCase("null") ? campos[2] : null);
        usuario.setRg(!campos[3].equalsIgnoreCase("null") ? campos[3] : null);
        usuario.setTelefone1(!campos[4].equalsIgnoreCase("null") ? campos[4] : null);
        usuario.setTelefone2(!campos[5].equalsIgnoreCase("null") ? campos[5] : null);
        usuario.setSituacao(getTipoSituacao(campos[6]));
        return usuario;

    }
    public static EnumTipoResidente getTipoResidente(String campo) {
        int ordinalTipoResidente = Integer.parseInt(campo);
        return EnumTipoResidente.valueOfResidente(ordinalTipoResidente);
    }
    public static EnumTipoSituacao getTipoSituacao(String campo) {
        int ordinalAtivo = Integer.parseInt(campo);
        return EnumTipoSituacao.valueOfAtivo(ordinalAtivo);
    }
    public static EnumTipoUsuarioSistema getTipoUsuariosistema(String campo) {
        int ordinalAtivo = Integer.parseInt(campo);
        return EnumTipoUsuarioSistema.valueOfUsuariosistema(ordinalAtivo);
    }

}
