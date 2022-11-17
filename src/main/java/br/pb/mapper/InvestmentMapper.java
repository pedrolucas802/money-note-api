package br.pb.mapper;

import br.pb.model.Investment;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.apache.ibatis.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
@RegisterForReflection(targets = {Investment.class})
public interface InvestmentMapper {

    @Select("Select * from investment")
    List<Investment> findAll();

    @Select("Select * from investment where idInvestment = #{idInvestment}")
    Optional<Investment> findOne(@Param("idInvestment") Long idInvestment);

    @Insert("Insert into investment(\n" +
            " description, value)\n" +
            "VALUES (#{investment.description},#{investment.value})")
    void save(@Param("investment") Investment investment);

    @Update("Update investment\n" +
            "set description= #{investment.description}, value= #{investment.value}\n" +
            "where idInvestment = #{investment.idInvestment}")
    void update(@Param("investment") Investment investment);

    @Delete("delete from investment where idInvestment = #{idInvestment}")
    void delete(@Param("idInvestment") Long idInvestment);

    @Select("select * from investment")
    Page<Investment> findAllPage(Pageable pageable);

}
