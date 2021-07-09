package com.perficient.simplecrudproject.services;

import com.perficient.simplecrudproject.model.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    public List<Address> getAddressById(Long id);

}
