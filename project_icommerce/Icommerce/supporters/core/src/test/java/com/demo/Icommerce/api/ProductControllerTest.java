package com.demo.Icommerce.api;

import com.demo.Icommerce.infrastructure.BaseTest;
import com.demo.Icommerce.infrastructure.util.JsonUtils;
import org.junit.jupiter.api.Test;

public class ProductControllerTest extends BaseTest {

    @Test
    public void createUser() {
        testFile("/product/1_product_testSet.json");
        System.out.println(JsonUtils.toJson(super.storedVariables));
    }
}
