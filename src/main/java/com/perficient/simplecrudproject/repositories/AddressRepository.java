package com.perficient.simplecrudproject.repositories;

import com.perficient.simplecrudproject.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

@Repository
public class AddressRepository {

    public RestTemplate restTemplate;

    @Autowired
    public AddressRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Address> getAddressesById(Long id){
        String url = "https://random-data-api.com/api/address/random_address";
        Random random = new Random();
        int numberOfAddresses = random.nextInt(4) + 1;
        ArrayList<Address> addresses = new ArrayList<>();
        IntStream.range(0,numberOfAddresses).forEach((i) -> addresses.add(restTemplate.getForObject(url,Address.class)));
        return  addresses;
    }

    @Async
    public Future<List<Address>> getAddressesByIdAsync(Long id){
        List<Address> addresses = getAddressesById(id);
        return new AsyncResult<>(addresses);
    }

}
