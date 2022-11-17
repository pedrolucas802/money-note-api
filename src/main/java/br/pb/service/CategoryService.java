package br.pb.service;

import br.pb.exception.APIException;
import br.pb.mapper.CategoryMapper;
import br.pb.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class CategoryService {
    @Inject
    CategoryMapper categoryMapper;

    public List<Category> findAll() {
        try {
            return categoryMapper.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("Error searching categories", e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public Category findOne(Long idCategory) {

        return categoryMapper.findOne(idCategory)
                .orElseThrow(() ->
                        new APIException("No category found", Response.Status.NO_CONTENT));

    }

    public void save(Category category) {
        try {
            categoryMapper.save(category);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("Error while adding the category", e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }


    public void update(long idCategory, Category category) {
        try {
            categoryMapper.update(category);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("Error trying to edit category.", e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public void delete(Long idCategory) {
        try {
            categoryMapper.delete(idCategory);
        } catch (Exception e) {
            e.printStackTrace();
            throw new APIException("Error trying to delete category.", e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public Page<Category> findAllPaged() {

        return categoryMapper.findAllPage(PageRequest.of(1,10));
    }
}
