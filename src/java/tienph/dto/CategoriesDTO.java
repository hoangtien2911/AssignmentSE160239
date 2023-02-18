/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienph.dto;

/**
 *
 * @author Hp
 */
public class CategoriesDTO {
    private int cateId;
    private String cateName;

    public CategoriesDTO() {
    }

    public CategoriesDTO(int cateId, String cateName) {
        this.cateId = cateId;
        this.cateName = cateName;
    }

    /**
     * @return the cateId
     */
    public int getCateId() {
        return cateId;
    }

    /**
     * @param cateId the cateId to set
     */
    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    /**
     * @return the cateName
     */
    public String getCateName() {
        return cateName;
    }

    /**
     * @param cateName the cateName to set
     */
    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    @Override
    public String toString() {
        return "CategoriesDTO{" + "cateId=" + cateId + ", cateName=" + cateName + '}';
    }
    
    
}
