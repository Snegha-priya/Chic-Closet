package com.chiccloset.serviceimpl;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chiccloset.ApiCaller;
import com.chiccloset.dto.OrderDTO;
import com.chiccloset.dto.OrderItemDTO;
import com.chiccloset.dto.ReportDTO;
import com.chiccloset.entitymodel.OrderItemModel;
import com.chiccloset.entitymodel.OrderModel;
import com.chiccloset.entitymodel.ReportModel;
import com.chiccloset.repository.OrderItemRepository;
import com.chiccloset.repository.OrderRepository;
import com.chiccloset.service.OrderService;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;
	private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	ApiCaller apiCaller;
	// list save
	public String save(OrderDTO orderDTO) {
		OrderModel orderModel=new OrderModel();
		ReportModel reportModel=new ReportModel();
	            orderModel = OrderModel.builder()
	            .id(orderDTO.getId())
	            .customerid(orderDTO.getCustomerId())
	            .orderdate(LocalDateTime.now())
	            .totalprice(orderDTO.getTotalPrice())
	            .itemname(orderDTO.getItemname())
	            .pin(orderDTO.getPin())
	            .createdby("todo")
	            .createdtime(LocalDateTime.now())
	            .active(true)
	            .build();
	            //report model
	            reportModel.setCreatedtime(LocalDateTime.now());
	            reportModel.setCustomerid(orderDTO.getCustomerId());
	            reportModel.setOrderitem(orderDTO.getItemname());
	            reportModel.setOrdertime(LocalDateTime.now());
	            reportModel.setActive(true);
	       
        orderRepository.save(orderModel);
        
	    return "Success";
	}

@Transactional
	public String remove(Long id) {
	 OrderModel orderModel = orderRepository.findByIdAndActive(id, true);
		if (orderModel != null) {
			orderModel.setActive(false);
			orderRepository.save(orderModel);
		}
		return "success"; 
	}
	// list

public List<OrderDTO> list() {
    List<OrderDTO> orderDTOList = new ArrayList<>();
    List<OrderItemDTO>orderItemDTOList=new ArrayList<>();
    logger.debug("orderRepository.findByActive");
    List<OrderModel> orderModelList = orderRepository.findByActive(true);
    for (OrderModel orderModel : orderModelList) {
        OrderDTO orderDTO = OrderDTO.builder()
                .id(orderModel.getId())
                .customerId(orderModel.getCustomerid())
                .orderDate(orderModel.getOrderdate())
                .totalPrice(orderModel.getTotalprice())
                .paymentmode(orderModel.getPaymentmode())
                .build();
        List<OrderItemModel> orderItemModelList = orderItemRepository.findByOrderid(orderModel.getId());    
        for (OrderItemModel orderItemModel : orderItemModelList) {
            OrderItemDTO orderItemDTO = OrderItemDTO.builder()
                    .id(orderItemModel.getId())
                    .productId(orderItemModel.getProductId())
                    .productName(orderItemModel.getProductName())
                    .quantity(orderItemModel.getQuantity())
                    .unitPrice(orderItemModel.getUnitPrice())
                    .build();

            // Fetch and set product image URL
            String imageUrl;
			try {
				imageUrl = apiCaller.invokeGetEndpoint(1, orderItemModel.getProductName());
				 orderItemDTO.setProductImageurl(imageUrl);
			} catch (SQLException e) {
				e.printStackTrace();
			}
           ;

            orderItemDTOList.add(orderItemDTO);
        }
        
        
        orderDTOList.add(orderDTO);
    }
    return orderDTOList;
}
}