package internet.shop;

import internet.shop.lib.Injector;
import internet.shop.model.Item;
import internet.shop.service.ItemService;

import java.math.BigDecimal;

public class MainApp {

    private static Injector injector = Injector.getInstance("internet.shop");

    public static void main(String[] args) {
        ItemService itemService = (ItemService) injector.getInstance(ItemService.class);
        generateItems(itemService,8);
        System.out.println(itemService.getAll().toString());
        System.out.println(itemService.get(3).toString());
        itemService.update((long) 3,new Item("Apple",new BigDecimal(55)));
        itemService.get(2).setCount(5);
        itemService.get(8).setCount(5);
        itemService.get(4).setCount(5);
        itemService.get(5).setCount(5);
        System.out.println(itemService.getAllAvailable().toString());
        itemService.deleteById(2);
        itemService.deleteByItem(itemService.get(6));
        System.out.println(itemService.getAll().toString());
    }

    public static void generateItems(ItemService itemService, int amount) {
        for (int i=0; i<amount; i++) {
            itemService.create(new Item("Item: " + i, new BigDecimal(i)));
        }
    }
}
