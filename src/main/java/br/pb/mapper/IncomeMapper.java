package br.pb.mapper;

import br.pb.model.Income;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Mapper
@RegisterForReflection(targets = {Income.class})
public interface IncomeMapper {

    @Select("Select * from income")
    List<Income> findAll();

    @Select("Select * from income where idIncome = #{idIncome}")
    Optional<Income> findOne(@Param("idIncome") Long idIncome);

    @Insert("Insert into income(\n" +
            " description, value, incomeconst)\n" +
            "VALUES (#{income.description},#{income.value}, #{income.incomeConst})")
    void save(@Param("income") Income income);

    @Update("Update income\n" +
            "set description= #{income.description}, value= #{income.value}, incomeConst= #{income.incomeConst}\n" +
            "where idIncome = #{income.idIncome}")
    void update(@Param("income") Income income);

    @Delete("delete from income where idIncome = #{idIncome}")
    void delete(@Param("idIncome") Long idIncome);

    @Select("select * from income")
    Page<Income> findAllPage(Pageable pageable);

}
