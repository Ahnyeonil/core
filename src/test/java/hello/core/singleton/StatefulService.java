package hello.core.singleton;

public class StatefulService {

    /* 주석은 싱글톤의 문제점 해결 */
//    private int price;

    public int order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
//        this.price = price;
        return price;
    }

//    public int getPrice(){
//        return price;
//    }
}
