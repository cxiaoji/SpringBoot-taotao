package com.itxj.pojo;

import java.io.Serializable;
import java.util.Date;

/*
 *  @项目名：  taotao-parent
 *  @包名：    com.itxj.pojo
 *  @文件名:   Cart
 *  @创建者:   小吉
 *  @创建时间:  2018/12/12 23:28
 *  @描述：    TODO
 */
public class Cart implements Serializable {

    private Long id;
    private Long userId;
    private Long itemId;
    private String itemTitle;
    private String itemImage;
    private Long itemPrice;
    private Integer num;
    private Date create;
    private Date update;

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", userId=" + userId +
                ", itemId=" + itemId +
                ", itemTitle='" + itemTitle + '\'' +
                ", itemImage='" + itemImage + '\'' +
                ", itemPrice=" + itemPrice +
                ", num=" + num +
                ", create=" + create +
                ", update=" + update +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public void setItemPrice(Long itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public String getItemImage() {
        return itemImage;
    }

    public Long getItemPrice() {
        return itemPrice;
    }

    public Integer getNum() {
        return num;
    }

    public Date getCreate() {
        return create;
    }

    public Date getUpdate() {
        return update;
    }
}
