package com.example.mongodb.mongodbtest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mongodb")
@RequiredArgsConstructor
public class AppApiController {
    private final AccountRepository accountRepository;
    private final ProductRepo productRepo;
    @GetMapping("/db")
    public String mongoDb(){
        return "example of mongo DB";
    }

    @PostMapping("/create")
public Account save(@RequestBody Account account){

	return accountRepository.save(account);

}
@GetMapping("/list")
public List<Account> getAllAccount(){

	return accountRepository.findAll();
}

@GetMapping("/user/{id}")
public Optional<Account> getUserById(@PathVariable("id") String id){
    return accountRepository.findById(id);

}

@PutMapping("/user-update")
public ResponseEntity<String> updateUser(@RequestBody Account account){
    Account existingAccount=accountRepository.findById(account.getId()).get();
    existingAccount.setPassword(account.getPassword());
    existingAccount.setUserName(account.getUserName());
    existingAccount.setId(account.getId());
    accountRepository.save(existingAccount);
    return new ResponseEntity<>("User updated successfully", HttpStatus.CREATED);
}

@GetMapping("/user-delete/{id}")
public String deleteUserById(@PathVariable("id") String id){
    accountRepository.deleteById(id);

    return "user Deleted.";

}
/*****
 *
 * requirements
 * 1 post product
 * 2 user buy product using online shoping system{using payment getways}
 * 3 product will be delivered.....
 * 
 * 
 */

@PostMapping("product/create")
public ResponseEntity<String> postProduct(@RequestBody Product product ){
    try
    {
    productRepo.save(product);

;     return new ResponseEntity<>("new product is created.",HttpStatus.CREATED);

    }catch(Exception e){
        System.out.println("error is occourd duto ..."+e.getMessage());
        return new ResponseEntity<>("error msg..."+e.getMessage(),HttpStatus.FORBIDDEN);
    }
}
@PutMapping("product/update")
public String editProduct(@RequestBody Product product){
Product existProduct=productRepo.findById(product.getId()).get();
if(existProduct!=null){
existProduct.setCategory(product.getCategory());
existProduct.setId(product.getId());
existProduct.setPrice(product.getPrice());
existProduct.setProductName(product.getProductName());
productRepo.save(existProduct);



    return new String("created Successfully.");

}else {
    return "could't update ,   "+product;
}
}


@DeleteMapping("/prodcut/delete/{id}")
public String deleteProduct(@PathVariable("id") String id){
    try{
        productRepo.deleteById(id);
        return "successfully removed!";
    }catch(Exception exception){
        return "deletion failed duto ..."+id;
    }
}

@GetMapping("/prodcut/product/{id}")
public Optional<Product> getProduct(@PathVariable("id") String id){
    try{
       return productRepo.findById(id);
    }catch(Exception exception){
       
        throw new RuntimeException("the error is occoured duto "+exception.getMessage());
    }
}

@GetMapping("/product/products")
public List<Product> getProducts(){
    return productRepo.findAll();
}

}
