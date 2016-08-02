package com.funeral.kris.model;

import javax.persistence.*;

/**
 * Created by kris.cheng on 7/11/2016.
 */
@Entity
@Table(name = "filters")
public class Filter {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "filter_code")
    private String filterCode;
    @Column(name = "general_code")
    private String generalCode;
    @Column(name = "filter_name")
    private String filterName;
    @Column(name = "filter_level")
    private Integer filterLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilterCode() {
        return filterCode;
    }

    public void setFilterCode(String filterCode) {
        this.filterCode = filterCode;
    }

    public String getGeneralCode() {
        return generalCode;
    }

    public void setGeneralCode(String generalCode) {
        this.generalCode = generalCode;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public Integer getFilterLevel() {
        return filterLevel;
    }

    public void setFilterLevel(Integer filterLevel) {
        this.filterLevel = filterLevel;
    }
}
