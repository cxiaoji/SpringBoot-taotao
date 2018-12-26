/******************************************************************
** 类    名：Test
** 描    述：
** 创 建 者：bianj
** 创建时间：2018-11-13 16:03:52
******************************************************************/

package com.itxj.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * (TB_TEST)
 * 
 * @author bianj
 * @version 1.0.0 2018-11-13
 */
@Entity
@Table(name = "TB_TEST")
public class Test implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 6118866204446427638L;
    
    /**  */
    @Column(name = "ID")
    private Integer id;
    
    /**  */
    @Column(name = "NAME")
    private String name;
    
    /**  */
    @Column(name = "URL")
    private String url;

    /**  */
    @Column(name = "NUM")
    private Integer num;


    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取
     * 
     * @return 
     */
    public Integer getId() {
        return this.id;
    }
     
    /**
     * 设置
     * 
     * @param id
     *          
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getName() {
        return this.name;
    }
     
    /**
     * 设置
     * 
     * @param name
     *          
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getUrl() {
        return this.url;
    }
     
    /**
     * 设置
     * 
     * @param url
     *          
     */
    public void setUrl(String url) {
        this.url = url;
    }
}