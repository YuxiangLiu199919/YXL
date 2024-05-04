package com.etc.order.service.manager;

public interface InventoryManager {

    /**
     * @param productId 商品id
     * @param quantity  商品数量
     * @return boolean
     * */
    boolean deductInventory(String  productId,Integer quantity);

}
