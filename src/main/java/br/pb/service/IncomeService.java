package br.pb.service;

import br.pb.exception.APIException;
import br.pb.mapper.IncomeMapper;
import br.pb.model.Income;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class IncomeService {
    @Inject
    IncomeMapper incomeMapper;

    public List<Income> findAll() {
        try {
            return incomeMapper.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("Error searching incomes", e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public Income findOne(Long idIncome) {

        return incomeMapper.findOne(idIncome)
                .orElseThrow(() ->
                        new APIException("Nenhum app encontrado", Response.Status.NO_CONTENT));

    }

    public void save(Income income) {
        try {
            incomeMapper.save(income);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("Error while adding the investment", e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }


    public void update(long idIncome, Income income) {
        try {
            incomeMapper.update(income);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("Error trying to edit income.", e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public void delete(Long idIncome) {
        try {
            incomeMapper.delete(idIncome);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("Error trying to delete income.", e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public Page<Income> findAllPaged() {

        return incomeMapper.findAllPage(PageRequest.of(1,10));
    }
}
