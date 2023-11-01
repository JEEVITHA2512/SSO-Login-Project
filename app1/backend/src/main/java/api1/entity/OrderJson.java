package api1.entity;

import api1.entity.Order;
import java.util.List;

public class OrderJson {
    private int status;
    private List<Order> data;

    public void setStatus(int status){
        this.status = status;
    }

    public void setData(List<Order> data){
        this.data = data;
    }
}
