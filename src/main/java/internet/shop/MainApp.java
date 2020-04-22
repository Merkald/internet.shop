package internet.shop;

import internet.shop.lib.Injector;
import internet.shop.service.ItemService;

public class MainApp {

    private static Injector injector = Injector.getInstance("internet.shop");

    public static void main(String[] args) {
        ItemService itemService = (ItemService) injector.getInstance(ItemService.class);
    }
}
