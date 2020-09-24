package catalog;

import java.util.Date;

public class ItemCatalog {


    private String itemName;
    private String designerName;
    private int price;
//
//
//    private int id;
//    private String alias;
//    private Date created_at;
//    private String ms_id;
//
//    private int show;
//    private Date updated_at;
//    private String url;


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDisignerName() {
        return designerName;
    }

    public void setDisignerName(String disignerName) {
        this.designerName = disignerName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{itemName: " + itemName
                + ", disignerName: " + designerName
                + ", price: " + price
//                + ", ms_id: " + ms_id
//                + ", name: " + name
//                + ", show: " + show
//                + ", updated_at: " + updated_at
//                + ", url: " + url
                + "}";
    }

    public ItemCatalog(String itemName, String disignerName, int price) {
        this.itemName = itemName;
        this.designerName = disignerName;
        this.price = price;
    }

    public ItemCatalog(){

    }

}
