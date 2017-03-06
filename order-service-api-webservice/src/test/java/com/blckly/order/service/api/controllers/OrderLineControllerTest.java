package com.blckly.order.service.api.controllers;

import com.blckly.order.service.api.OrderServiceApiApplication;
import com.blckly.order.service.api.domain.Order;
import com.blckly.order.service.api.model.AddressDTO;
import com.blckly.order.service.api.model.OrderDTO;
import com.blckly.order.service.api.model.OrderLineDTO;
import com.blckly.order.service.api.model.OrderLineKitDTO;
import com.blckly.order.service.api.model.OrderLineKitOptionDTO;
import com.blckly.order.service.api.model.OrderLineKitOptionItemsDTO;
import com.blckly.order.service.api.respositories.OrderLineRepository;
import com.blckly.order.service.api.respositories.OrderRepository;
import com.blckly.order.service.api.type.KitOptionCategory;
import com.blckly.order.service.api.type.OrderLineStatus;
import com.blckly.order.service.api.type.OrderStatus;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;

/**
 * Created by alexnikolayevsky on 3/5/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {OrderServiceApiApplication.class})
@TestPropertySource(locations="classpath:application-test.properties")
@WebIntegrationTest
public class OrderLineControllerTest {
    @Autowired
    private OrderLineController controller;

    @Autowired
    private OrderController orderController;

    @Autowired
    protected OrderRepository orderRepository;

    @Autowired
    protected OrderLineRepository repository;

    @Before
    public void initialize() {
        orderController.saveOrder(generateOrder());
    }

    @After
    public void tearDown() {
        orderRepository.deleteAll();
        repository.deleteAll();
    }

    @Test
    public void testSaveOrderLine() {
        OrderLineDTO[] orderLineDTOs = controller.saveOrderLines(1, generateOrderLines());
        Assert.notNull(orderLineDTOs);
        Assert.isTrue(orderLineDTOs.length == 1);
    }

    @Test
    public void testGetOrderLine() {
        OrderLineDTO orderLineDTO = controller.getOrderLine(1, 1);
        Assert.isNull(orderLineDTO);

        OrderLineDTO[] orderLineDTOs = controller.saveOrderLines(1, generateOrderLines());
        orderLineDTO = controller.getOrderLine(1, orderLineDTOs[0].getId());
        Assert.notNull(orderLineDTO);
    }

    @Test
    public void testFindOrderLines() {
        OrderLineDTO[] orderLineDTOs = controller.findOrderLines(1, 1, OrderLineStatus.PENDING);
        Assert.isTrue(orderLineDTOs.length == 0);

        controller.saveOrderLines(1, generateOrderLines());
        orderLineDTOs = controller.findOrderLines(1, 1, OrderLineStatus.PENDING);
        Assert.notNull(orderLineDTOs);
        Assert.isTrue(orderLineDTOs.length == 1);

        orderLineDTOs = controller.findOrderLines(1, 1, null);
        Assert.notNull(orderLineDTOs);
        Assert.isTrue(orderLineDTOs.length == 1);

        orderLineDTOs = controller.findOrderLines(null, 1, OrderLineStatus.PENDING);
        Assert.notNull(orderLineDTOs);
        Assert.isTrue(orderLineDTOs.length == 1);

        orderLineDTOs = controller.findOrderLines(null, 1, null);
        Assert.notNull(orderLineDTOs);
        Assert.isTrue(orderLineDTOs.length == 1);

    }

    @Test
    public void testUpdateOrderLine() {
        OrderLineDTO[] orderLineDTOs =  generateOrderLines();
        try {
            controller.updateOrderLine(1, 1, null);
            org.junit.Assert.fail("Should fail");
        } catch (Exception e) {
            Assert.isInstanceOf(NullPointerException.class, e);
        }

        // Test: update with null/different order id vs the provided id
        try {
            controller.updateOrderLine(1, 1, orderLineDTOs[0]);
            org.junit.Assert.fail("fail");
        } catch (Exception e) {
            Assert.isInstanceOf(Exception.class, e);
        }

        // Test: update a nonexistent order
        try {
            orderLineDTOs[0].setId(1);
            controller.updateOrderLine(1, 1, orderLineDTOs[0]);
            org.junit.Assert.fail("Should fail");
        } catch (Exception e) {
            Assert.isInstanceOf(EntityNotFoundException.class, e);
        }

        // Test: update a order
        orderLineDTOs = controller.saveOrderLines(1, orderLineDTOs);
        Double originalPrice = orderLineDTOs[0].getPrice();
        orderLineDTOs[0].setPrice(40.00);
        try {
            OrderLineDTO orderLineDTO = controller.updateOrderLine(orderLineDTOs[0].getId(), 1, orderLineDTOs[0]);
            Assert.isTrue(!orderLineDTO.getPrice().equals(originalPrice));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }

    }

    private OrderDTO generateOrder() {
        OrderDTO order = new OrderDTO();
        order.setId(1);
        order.setPrice(30.00);
        order.setRequesterId(1);
        order.setStatus(OrderStatus.PROCESSING);
        return order;
    }

    private OrderLineDTO[] generateOrderLines() {
        OrderLineDTO orderLine = new OrderLineDTO();
        orderLine.setOrderId(1);
        orderLine.setPrice(30.00);
        orderLine.setCustomerId(1);
        orderLine.setStatus(OrderLineStatus.PENDING);
        orderLine.setDestinationFirstName("First");
        orderLine.setDestinationLastName("Last");
        orderLine.setDestinationEmail("foo@bar.com");
        orderLine.setDestinationPhone("3033333333");
        orderLine.setDestinationAddress(generateAddress());
        orderLine.setOrderLineKits(generateKits());
        List<OrderLineDTO> dtos = new ArrayList<OrderLineDTO>();
        dtos.add(orderLine);
        return dtos.toArray(new OrderLineDTO[dtos.size()]);
    }

    private AddressDTO generateAddress() {
        AddressDTO address = new AddressDTO();
        address.setAddressLine1("addr 1");
        address.setAddressLine2("addr 2");
        address.setCity("Denver");
        address.setCountry("USA");
        address.setState("CO");
        return address;
    }

    private List<OrderLineKitDTO> generateKits() {
        List<OrderLineKitDTO> orderLineKitDTOs = new ArrayList<OrderLineKitDTO>();
        OrderLineKitDTO orderLineKitDTO = new OrderLineKitDTO();
        orderLineKitDTO.setName("AhDee");
        orderLineKitDTO.setDescription("AhDee German Engineering");
        orderLineKitDTO.setQty(1);
        orderLineKitDTO.setSku("AHDEE");
        orderLineKitDTO.setOrderLineKitOptions(generateKitOptions());
        orderLineKitDTO.setBaseFee(3.33);
        orderLineKitDTOs.add(orderLineKitDTO);
        return orderLineKitDTOs;
    }


    private List<OrderLineKitOptionDTO> generateKitOptions() {
        OrderLineKitOptionDTO kitOption = new OrderLineKitOptionDTO();
        kitOption.setName("Wheels");
        kitOption.setDescription("Wheels");
        kitOption.setBaseFee(3.50);
        kitOption.setCategory(KitOptionCategory.WHEELS);
        kitOption.setOrderLineKitOptionItems(generateWheelItems());

        OrderLineKitOptionDTO kitOption1 = new OrderLineKitOptionDTO();
        kitOption1.setName("Engine Kit");
        kitOption1.setDescription("Engine Kit");
        kitOption1.setBaseFee(30.00);
        kitOption1.setCategory(KitOptionCategory.BATTERY);
        kitOption1.setOrderLineKitOptionItems(generateBatteryKitItems());

        OrderLineKitOptionDTO kitOption2 = new OrderLineKitOptionDTO();
        kitOption2.setName("Body Kit");
        kitOption2.setDescription("Body type");
        kitOption2.setBaseFee(4.50);
        kitOption2.setCategory(KitOptionCategory.BODY);
        kitOption2.setOrderLineKitOptionItems(generateBodyItems());

        OrderLineKitOptionDTO kitOption3 = new OrderLineKitOptionDTO();
        kitOption3.setName("Stickers");
        kitOption3.setDescription("Stickers");
        kitOption3.setBaseFee(4.50);
        kitOption3.setCategory(KitOptionCategory.STICKERS);
        kitOption3.setOrderLineKitOptionItems(generateStickerItems());

        OrderLineKitOptionDTO kitOption4 = new OrderLineKitOptionDTO();
        kitOption4.setName("Sound System");
        kitOption4.setDescription("Sound System");
        kitOption4.setBaseFee(4.50);
        kitOption4.setCategory(KitOptionCategory.SOUND_SYSTEM);
        kitOption4.setOrderLineKitOptionItems(generateSoundItems());

        List<OrderLineKitOptionDTO> kitOptions = new ArrayList<OrderLineKitOptionDTO>();
        kitOptions.add(kitOption);
        kitOptions.add(kitOption1);
        kitOptions.add(kitOption2);
        kitOptions.add(kitOption3);
        kitOptions.add(kitOption4);
        return kitOptions;
    }

    private List<OrderLineKitOptionItemsDTO> generateWheelItems() {
        OrderLineKitOptionItemsDTO kitOptionItem = new OrderLineKitOptionItemsDTO();
        kitOptionItem.setName("4 Wheels");
        kitOptionItem.setDescription("4 AhDee Wheels");
        kitOptionItem.setBaseFee(2.50);

        List<OrderLineKitOptionItemsDTO> kitOptionItems = new ArrayList<OrderLineKitOptionItemsDTO>();
        kitOptionItems.add(kitOptionItem);
        return kitOptionItems;
    }

    private List<OrderLineKitOptionItemsDTO> generateBatteryKitItems() {
        OrderLineKitOptionItemsDTO kitOptionItem = new OrderLineKitOptionItemsDTO();
        kitOptionItem.setName("8Volt –Standard 4AA battery kit");
        kitOptionItem.setDescription("8Volt Batteries");
        kitOptionItem.setBaseFee(2.50);

        List<OrderLineKitOptionItemsDTO> kitOptionItems = new ArrayList<OrderLineKitOptionItemsDTO>();
        kitOptionItems.add(kitOptionItem);
        return kitOptionItems;
    }

    private List<OrderLineKitOptionItemsDTO> generateBodyItems() {
        OrderLineKitOptionItemsDTO kitOptionItem = new OrderLineKitOptionItemsDTO();
        kitOptionItem.setName("FastBack");
        kitOptionItem.setDescription("FastBack Body");
        kitOptionItem.setBaseFee(2.50);

        List<OrderLineKitOptionItemsDTO> kitOptionItems = new ArrayList<OrderLineKitOptionItemsDTO>();
        kitOptionItems.add(kitOptionItem);
        return kitOptionItems;
    }

    private List<OrderLineKitOptionItemsDTO> generateStickerItems() {
        OrderLineKitOptionItemsDTO kitOptionItem = new OrderLineKitOptionItemsDTO();
        kitOptionItem.setName("Standard");
        kitOptionItem.setDescription("Standard Sticker");
        kitOptionItem.setBaseFee(2.50);

        List<OrderLineKitOptionItemsDTO> kitOptionItems = new ArrayList<OrderLineKitOptionItemsDTO>();
        kitOptionItems.add(kitOptionItem);
        return kitOptionItems;
    }

    private List<OrderLineKitOptionItemsDTO> generateSoundItems() {
        OrderLineKitOptionItemsDTO kitOptionItem = new OrderLineKitOptionItemsDTO();
        kitOptionItem.setName("Bows");
        kitOptionItem.setDescription("Bows Sound System");
        kitOptionItem.setBaseFee(2.50);

        List<OrderLineKitOptionItemsDTO> kitOptionItems = new ArrayList<OrderLineKitOptionItemsDTO>();
        kitOptionItems.add(kitOptionItem);
        return kitOptionItems;
    }
}
