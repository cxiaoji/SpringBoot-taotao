
package com.itxj.pojo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/******************************************************************
 ** 类    名：MailEntity
 ** 描    述：
 ** 创 建 者：bianj
 ** 创建时间：2018-12-09 23:34:15
 **************************************************************

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * (TB_MAIL)
 *
 * @author bianj
 * @version 1.0.0 2018-12-09
 */
@Entity
@Table(name = "TB_MAIL")
public class Mail implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 5652343334110081514L;

    /**  */
    @Column(name = "ID")
    private Integer id;

    /**  */
    @Column(name = "QQ")
    private String qq;

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
    public String getQq() {
        return this.qq;
    }

    /**
     * 设置
     *
     * @param qq
     *
     */
    public void setQq(String qq) {
        this.qq = qq;
    }
}