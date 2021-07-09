package com.perficient.simplecrudproject.serviceImpls;

import com.perficient.simplecrudproject.model.Address;
import com.perficient.simplecrudproject.repositories.AddressRepository;
import com.perficient.simplecrudproject.services.AddressService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAddressById(Long id) {
        Assert.notNull(id, "ID cannot be null");
        return addressRepository.getAddressesById(id);
    }
}
