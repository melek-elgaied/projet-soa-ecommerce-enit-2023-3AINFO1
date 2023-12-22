package com.enit.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import io.vertx.redis.client.Response;
import com.enit.Domain.Cart;
import com.enit.Domain.Product;

import io.quarkus.redis.client.RedisClient;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.text.ParseException;
@ApplicationScoped
public class CartRepository implements CartInterface {
   
    @Inject
    RedisClient redisClient;

    public Cart openCart(String customerId) {
        //UUID cartId = UUID.randomUUID();
        Cart newCart = new Cart(customerId);

        Response cartResponse = redisClient.hgetall(customerId);
        String cart = cartResponse != null ? cartResponse.toString() : null;

        // it means that there is no cart for this user bc it returns {} in case of non existance
       if(cart.length()== 2){
            String cartKey = customerId;
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedModificationDate = dateFormat.format(newCart.getModificationDate());

            System.out.println(formattedModificationDate);
            
            // Add the feilds "Modification Date" & "Tolal Price"
            redisClient.hsetnx(customerId, "ModificationDate", formattedModificationDate);
            redisClient.hsetnx(cartKey, "TolalPrice", Integer.toString(0));   
            
           
       }else{

        this.getCart(customerId);
           
       }
        return newCart;
        
    }

    public void closeCart(String customerId) {
        
        List<String> keysToDelete = Collections.singletonList(customerId);
        redisClient.del(keysToDelete);

    }

    public void addItem(String customerId, Long productId, Integer quantity){
        System.out.println(customerId);
  
        Cart cart= this.getCart(customerId);
        //add the item to the cart
        cart.addProduct(productId, quantity);

        


        //save the changes in redis
        List<String> saveRedis = new ArrayList<>();

        saveRedis.add(customerId);
      
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedModificationDate = dateFormat.format(cart.getModificationDate());

        saveRedis.add("ModificationDate");
        saveRedis.add(formattedModificationDate);

        //add total price
        saveRedis.add("TolalPrice");
        saveRedis.add(Double.toString(cart.getTotalPrice()));

        //add products to redis
        for(Product product: cart.getProducts()){
            saveRedis.add(product.getIdProduct().toString());
            saveRedis.add(Integer.toString(product.getQuantity()) +"|"+ Double.toString(product.getPrice()));
        }

        System.out.println("saveRedis");
        System.out.println(saveRedis);
        
        redisClient.hset(saveRedis);

        
        System.out.println("added");


    };

     public void removeItem(String customerId, Long productId, Integer quantity){
    
        Cart cart= this.getCart(customerId);
   
        cart.removeProduct(productId, quantity);

        

        //save the changes in redis
        List<String> saveRedis = new ArrayList<>();

       
        saveRedis.add(customerId);

        //add modification date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedModificationDate = dateFormat.format(cart.getModificationDate());

        saveRedis.add("ModificationDate");
        saveRedis.add(formattedModificationDate);

   
        saveRedis.add("TolalPrice");
        saveRedis.add(Double.toString(cart.getTotalPrice()));

     
        for(Product product: cart.getProducts()){
            saveRedis.add(product.getIdProduct().toString());
            saveRedis.add(Integer.toString(product.getQuantity()) +"|"+ Double.toString(product.getPrice()));

            System.out.println(product.getIdProduct());
            System.out.println(product.getQuantity());
        }

        System.out.println(saveRedis);
        redisClient.hset(saveRedis);


    };


    

  
    public Cart getCart(String customerId){
        Response cartKeysResponse= redisClient.hkeys(customerId);
        String cartKeys= cartKeysResponse != null ? cartKeysResponse.toString() : null;
        Cart cart= new Cart(customerId);

        System.out.println(cartKeysResponse);

        if(cartKeys.length()==2){
            System.out.println("open in get");
            cart= this.openCart(customerId);

        }else{
            try{
           System.out.println("not open in get");
            List<Product> items= new ArrayList<>();
            Product product= new Product();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
            System.out.println(redisClient.hget(customerId, "ModificationDate").toString());
            cart.setModificationDate(dateFormat.parse(redisClient.hget(customerId, "ModificationDate").toString()));
            cart.setTotalPrice(Double.parseDouble(redisClient.hget(customerId, "TolalPrice").toString()));

            System.out.println(redisClient.hkeys(customerId));
            for(Response i : cartKeysResponse){
                System.out.println(i.toString());
                if(!((i.toString().equalsIgnoreCase("ModificationDate")) || (i.toString().equalsIgnoreCase("TolalPrice")))){
                    product.setIdProduct(Long.parseLong(i.toString()));

                    String productValue= redisClient.hget(customerId, i.toString()).toString();
                    String[] productValueList= productValue.split("\\|");


                    System.out.println(productValue);

                    product.setQuantity(Integer.parseInt(productValueList[0]));
                    product.setPrice(Double.parseDouble(productValueList[1]));

                    items.add(product);
                }
            }
            cart.setProducts(items);
       
            }catch(ParseException e){
                //e.printStackTrace();
                System.err.println("Wrong MOdification Date Format!");
            }
        }
        return cart;
    }

    

    
    
        }
    
        
    
    
    



    






