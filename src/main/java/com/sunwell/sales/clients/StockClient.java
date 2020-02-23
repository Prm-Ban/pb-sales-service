package com.sunwell.sales.clients;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sunwell.sales.model.CartDetail;
import com.sunwell.sales.utils.UserContext;


@Component
public class StockClient {
    @Autowired
    RestTemplate restTemplate;

//    @Autowired
//    OrganizationRedisRepository orgRedisRepo;

    private static final Logger logger = LoggerFactory.getLogger(StockClient.class);
//
//    private Organization checkRedisCache(String organizationId) {
//        try {
//            return orgRedisRepo.findOrganization(organizationId);
//        }
//        catch (Exception ex){
//            logger.error("Error encountered while trying to retrieve organization {} check Redis Cache.  Exception {}", organizationId, ex);
//            return null;
//        }
//    }

//    private void cacheOrganizationObject(Organization org) {
//        try {
//            orgRedisRepo.saveOrganization(org);
//        }catch (Exception ex){
//            logger.error("Unable to cache organization {} in Redis. Exception {}", org.getId(), ex);
//        }
//    }

//    public SalesInvoice getSalesOrder(long _soId){
//        logger.debug("In Licensing Service.getOrganization: {}", UserContext.getCorrelationId());
//
////        Organization org = checkRedisCache(organizationId);
////
////        if (org!=null){
////            logger.debug("I have successfully retrieved an organization {} from the redis cache: {}", organizationId, org);
////            return org;
////        }
////
////        logger.debug("Unable to locate organization from the redis cache: {}.", organizationId);
//
//        ResponseEntity<SalesInvoice> restExchange =
//                restTemplate.exchange(
//                        "http://zuul-service/sales-service/salesservice/resources/salesorders?systemId={systemId}",
//                        HttpMethod.GET,
//                        null, SalesInvoice.class, _soId);
//
//        /*Save the record from cache*/
//        SalesInvoice si  = restExchange.getBody();
//       
//        return si;
//    }
//    
//    public SalesInvoice deleteSalesOrder(long _soId){
//        logger.debug("In Licensing Service.getOrganization: {}", UserContext.getCorrelationId());
//
////        Organization org = checkRedisCache(organizationId);
////
////        if (org!=null){
////            logger.debug("I have successfully retrieved an organization {} from the redis cache: {}", organizationId, org);
////            return org;
////        }
////
////        logger.debug("Unable to locate organization from the redis cache: {}.", organizationId);
//
//        ResponseEntity<SalesInvoice> restExchange =
//                restTemplate.exchange(
//                        "http://zuul-service/sales-service/salesservice/resources/salesorders?systemId={systemId}",
//                        HttpMethod.DELETE,
//                        null, SalesInvoice.class, _soId);
//
//        /*Save the record from cache*/
//        SalesInvoice si  = restExchange.getBody();
//       
//        return si;
//    }
    
    public Map<Integer, Double> getStocks(List<Integer> _items) throws Exception {
    	if(_items == null || _items.size() <= 0)
    		return null;
    	
    	String url = "http://zuul-service/stock-service/stockservice/resources/stocksbyid?warehouseId=1";
//    	String url = "http://localhost:5555/stock-service/stockservice/resources/stocksbyid?warehouseId=1";
    	for(Integer id: _items) {
    		url += "&itemId=" + id; 
    	}
    	
    	
    	ResponseEntity<String> response =
              restTemplate.exchange(
            		  url,
                      HttpMethod.GET,
                      null, String.class, _items);
    	
    	System.out.println("Response: " + response.getBody());

		ObjectMapper om = new ObjectMapper();
		JsonNode root = om.readTree(response.getBody());
		ArrayNode items = (ArrayNode)root.get("data");
		if(items.size() <= 0)
			return null;
		
		Map<Integer, Double> stocks = new HashMap<>();
		for(JsonNode node: items) {
			stocks.put(node.get("idItem").intValue(), node.get("qty").doubleValue());
		}
		
		for(Integer k: stocks.keySet()) {
			System.out.println("KEY: " + k + " VAL: " + stocks.get(k));
		}
		return stocks;
    }
    
    public void deleteStocks(List<CartDetail> _cartDetails){
//      logger.debug("In Licensing Service.getOrganization: {}", UserContext.getCorrelationId());

//      Organization org = checkRedisCache(organizationId);
//
//      if (org!=null){
//          logger.debug("I have successfully retrieved an organization {} from the redis cache: {}", organizationId, org);
//          return org;
//      }
//
//      logger.debug("Unable to locate organization from the redis cache: {}.", organizationId);
      
      if(_cartDetails == null || _cartDetails.size() <= 0)
    	  return;
      
      List<Map<String,String>> data = new LinkedList<>();
      for(CartDetail cd : _cartDetails) {
    	  Map<String,String> map = new HashMap<>();
    	  map.put("idItem", "" + cd.getIdItem());
    	  map.put("qty", ""+ cd.getQty());
    	  map.put("warehouse", "Utama");
    	  data.add(map);
      }
      
      HttpEntity<List<Map<String,String>>> body = new HttpEntity<>(data);
      
      ResponseEntity<String> restExchange =
              restTemplate.exchange(
                      "http://zuul-service/stock-service/stockservice/resources/removestocks",
                      HttpMethod.POST,
                      body, String.class);
      
//      ResponseEntity<String> restExchange =
//              restTemplate.exchange(
//                      "http://localhost:5555/stock-service/stockservice/resources/removestocks",
//                      HttpMethod.POST,
//                      body, String.class);
//
      
////
//      /*Save the record from cache*/
//      SalesInvoice si  = restExchange.getBody();
     
//      return si;
  }


}
