package br.pb.service;

import br.pb.exception.APIException;
import br.pb.mapper.PurchaseMapper;
import br.pb.model.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class PurchaseService {
    @Inject
    PurchaseMapper purchaseMapper;

    public List<Purchase> findAll() {
        try {
            return purchaseMapper.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("Error searching Purchases", e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public Purchase findOne(Long idPurchase) {

        return purchaseMapper.findOne(idPurchase)
                .orElseThrow(() ->
                        new APIException("No purchase found.", Response.Status.NO_CONTENT));

    }

    public void save(Purchase Purchase) {
        try {
            purchaseMapper.save(Purchase);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("Error while adding the Purchase", e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }


    public void update(long idPurchase, Purchase Purchase) {
        try {
            purchaseMapper.update(Purchase);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("Error trying to edit Purchase.", e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public void delete(Long idPurchase) {
        try {
            purchaseMapper.delete(idPurchase);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("Error trying to delete Purchase.", e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public Page<Purchase> findAllPaged() {

        return purchaseMapper.findAllPage(PageRequest.of(1,10));
    }
}
