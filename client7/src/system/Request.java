package system;

import classes.Product;

import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 7460996574894336141L;
    public Product product;
    public User user;
    String[] args;

    //Передавать команды и аргументы стрингом или массивом стрингов

    public Request(Product product, User user){
        this.product = product;
    }

    public Request(Product product,String[] args, User user){
        this.product = product;
        this.args = args;
        this.user = user;
    }
    public Request(String[] args, User user){
        this.args =args;
        this.user = user;
    }


    public String[] getArgs() {
        return args;
    }
    public void setArgs(String[] args) {
        this.args = args;
    }

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }
}
