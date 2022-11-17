package br.pb.mapper;

import br.pb.model.Category;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.apache.ibatis.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
@RegisterForReflection(targets = {Category.class})
public interface CategoryMapper {

    @Select("Select * from category")
    List<Category> findAll();

    @Select("Select * from category where idCategory = #{idCategory}")
    Optional<Category> findOne(@Param("idCategory") Long idCategory);

    @Insert("Insert into category(\n" +
            " description)\n" +
            "VALUES (#{category.description})")
    void save(@Param("category") Category category);

    @Update("Update category\n" +
            "set description= #{category.description}\n" +
            "where idCategory = #{category.idCategory}")
    void update(@Param("category") Category category);

    @Delete("delete from category where idCategory = #{idCategory}")
    void delete(@Param("idCategory") Long idCategory);

    @Select("select * from category")
    Page<Category> findAllPage(Pageable pageable);

}
