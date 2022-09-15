package br.unifor.repository.mapper;

import br.unifor.model.excecao.ModalidadeExcecao;
import br.unifor.model.excecao.ModalidadeTipoExcecao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;

@Mapper
public interface ModalidadeExcecaoMapper {


    public static final String QUERY = """
            WITH fat_excecoes  as(
            SELECT nvl(mt.ID_MODALIDADE_TIPO, mi.ID_MODALIDADE_TIPO) ID_MODALIDADE_TIPO ,
            	   nvl(mt.ID_MODALIDADE,mi.ID_MODALIDADE)  ID_MODALIDADE, 
                   nvl(mt.NM_MODALIDADE, mi.NM_MODALIDADE) NM_MODALIDADE
              FROM ca.FAT_REGRA_NAO_PERMITE r INNER JOIN ca.FAT_MODALIDADE m ON m.ID_MODALIDADE_TIPO = r.ID_MODALIDADE_TIPO
                                               LEFT JOIN ca.FAT_MODALIDADE mt ON r.ID_MODALIDADE_TIPO_NAO = mt.ID_MODALIDADE_TIPO
                                               LEFT JOIN ca.FAT_MODALIDADE mi ON r.ID_MODALIDADE_NAO = mi.ID_MODALIDADE
             WHERE m.ID_MODALIDADE = #{idModalidade}
                OR r.id_modalidade = #{idModalidade})
             SELECT fmt.ID_MODALIDADE_TIPO idModalidadeTipo, fmt.NM_MODALIDADE_TIPO nmModalidadeTipo, e.id_modalidade idModalidade, e.nm_modalidade nmModalidade
               FROM fat_excecoes e INNER JOIN ca.FAT_MODALIDADE_TIPO fmt ON e.id_modalidade_tipo = fmt.ID_MODALIDADE_TIPO
             ORDER BY 2, 4
            """;
    @Select("""
             SELECT m.ID_MODALIDADE_TIPO idModalidadeTipo, m.NM_MODALIDADE_TIPO nmModalidadeTipo
              FROM ca.FAT_REGRA_NAO_PERMITE r INNER JOIN ca.FAT_MODALIDADE_TIPO  m ON m.ID_MODALIDADE_TIPO = r.ID_MODALIDADE_TIPO_NAO
             WHERE r.ID_MODALIDADE_TIPO  = #{tipoModalidade}                       
            """)
    Collection<ModalidadeTipoExcecao> getExcecoesTipoModalidadeTipos(Long tipoModalidade);

    @Select("""
             SELECT m.ID_MODALIDADE_TIPO idModalidadeTipo, t.NM_MODALIDADE_TIPO nmModalidadeTipo,
                    m.ID_MODALIDADE idModalidade, m.NM_MODALIDADE nmModalidade
               FROM ca.FAT_REGRA_NAO_PERMITE r INNER JOIN ca.FAT_MODALIDADE  m ON m.ID_MODALIDADE = r.ID_MODALIDADE_NAO
                                               INNER JOIN ca.FAT_MODALIDADE_TIPO t ON t.ID_MODALIDADE_TIPO = m.ID_MODALIDADE_TIPO
              WHERE r.ID_MODALIDADE_TIPO  = #{tipoModalidade}                       
            """)
    Collection<ModalidadeExcecao> getExcecoesTipoModalidade(Long tipoModalidade);


    @Select("""
             SELECT m.ID_MODALIDADE_TIPO idModalidadeTipo, m.NM_MODALIDADE_TIPO nmModalidadeTipo
              FROM ca.FAT_REGRA_NAO_PERMITE r INNER JOIN ca.FAT_MODALIDADE_TIPO  m ON m.ID_MODALIDADE_TIPO = r.ID_MODALIDADE_TIPO_NAO
             WHERE r.ID_MODALIDADE  = #{idModalidade}                       
            """)
    Collection<ModalidadeTipoExcecao> getExcecoesModalidadeTipos(Long idModalidade);

    @Select("""
             SELECT m.ID_MODALIDADE_TIPO idModalidadeTipo, t.NM_MODALIDADE_TIPO nmModalidadeTipo,
                    m.ID_MODALIDADE idModalidade, m.NM_MODALIDADE nmModalidade
               FROM ca.FAT_REGRA_NAO_PERMITE r INNER JOIN ca.FAT_MODALIDADE  m ON m.ID_MODALIDADE = r.ID_MODALIDADE_NAO
                                               INNER JOIN ca.FAT_MODALIDADE_TIPO t ON t.ID_MODALIDADE_TIPO = m.ID_MODALIDADE_TIPO
              WHERE r.ID_MODALIDADE  = #{idModalidade}                       
            """)
    Collection<ModalidadeExcecao> getExcecoesModalidade(Long idModalidade);

    @Select(QUERY)
    Collection<ModalidadeExcecao> regraModaldiade(Long idModalidade);
}
