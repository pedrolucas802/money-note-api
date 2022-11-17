package br.pb.mapper;

import br.pb.model.Income;
import br.pb.model.Purchase;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.apache.ibatis.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
@RegisterForReflection(targets = {Purchase.class})
public interface PurchaseMapper {

    @Select("Select * from purchase")
    List<Purchase> findAll();

    @Select("Select * from purchase where idPurchase = #{idPurchase}")
    Optional<Purchase> findOne(@Param("idPurchase") Long idPurchase);

    @Insert("Insert into purchase(\n" +
            " description, value, purchaseConst)\n" +
            "VALUES (#{purchase.description},#{purchase.value}, #{purchase.purchaseConst})")
    void save(@Param("purchase") Purchase purchase);

    @Update("Update purchase\n" +
            "set description= #{purchase.description}, value= #{purchase.value}, purchaseConst= #{purchase.purchaseConst}\n" +
            "where idPurchase = #{purchase.idPurchase}")
    void update(@Param("purchase") Purchase purchase);

    @Delete("delete from purchase where idPurchase = #{idPurchase}")
    void delete(@Param("idPurchase") Long idPurchase);

    @Select("select * from purchase")
    Page<Purchase> findAllPage(Pageable pageable);

}
