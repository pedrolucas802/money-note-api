package br.pb.service;

import br.pb.exception.APIException;
import br.pb.mapper.IncomeMapper;
import br.pb.model.Income;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class IncomeService {
    @Inject
    IncomeMapper incomeMapper;

    public void save(Income income) {
        try {
            incomeMapper.save(income);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("Error while adding the investment", e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Income> findAll() {
        try {
            return incomeMapper.findAll();

        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("Error searching incomes", e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
