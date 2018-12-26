package com.itxj.pojo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

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
    private String Image;
    private String[] Images;
    private Long itemPrice;
    private Integer num;
    private Date create;
    private Date update;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(itemId, cart.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getImage() {
        return Image;
    }


    public String[] getImages() {
        return Images;
    }

    public void setImages(String[] images) {
        Images = images;
    }

    public Long getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Long itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }

    public void setImage(String image) {
        Image = image;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", userId=" + userId +
                ", itemId=" + itemId +
                ", itemTitle='" + itemTitle + '\'' +
                ", Image='" + Image + '\'' +
                ", Images=" + Arrays.toString(Images) +
                ", itemPrice=" + itemPrice +
                ", num=" + num +
                ", create=" + create +
                ", update=" + update +
                '}';
    }
}
