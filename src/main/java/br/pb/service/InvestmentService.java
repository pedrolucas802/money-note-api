package br.pb.service;

import br.pb.exception.APIException;
import br.pb.mapper.InvestmentMapper;
import br.pb.model.Investment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class InvestmentService {
    @Inject
    InvestmentMapper investmentMapper;

    public List<Investment> findAll() {
        try {
            return investmentMapper.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("Error searching Investments", e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public Investment findOne(Long idInvestment) {

        return investmentMapper.findOne(idInvestment)
                .orElseThrow(() ->
                        new APIException("No investment found", Response.Status.NO_CONTENT));

    }

    public void save(Investment Investment) {
        try {
            investmentMapper.save(Investment);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("Error while adding the investment", e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }


    public void update(long idInvestment, Investment Investment) {
        try {
            investmentMapper.update(Investment);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("Error trying to edit Investment.", e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public void delete(Long idInvestment) {
        try {
            investmentMapper.delete(idInvestment);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("Error trying to delete Investment.", e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public Page<Investment> findAllPaged() {

        return investmentMapper.findAllPage(PageRequest.of(1,10));
    }
}
