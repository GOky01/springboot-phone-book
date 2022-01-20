package com.springboot.phonebook.service;
import com.springboot.phonebook.entity.Phone;
import com.springboot.phonebook.repository.PhoneRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;

    public PhoneServiceImpl(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    @Transactional
    public List<Phone> getPhones() {
        return phoneRepository.findAll();
    }

    @Override
    @Transactional
    public void savePhone(Phone phone) {
        phoneRepository.save(phone);
    }

    @Override
    @Transactional
    public Phone getPhone(int theId) throws NotFoundException {
        return phoneRepository.findById(theId).orElseThrow(()
                -> new NotFoundException("Phone was not fount with id :" + theId));
    }

    @Override
    @Transactional
    public void deletePhone(int theId) {
        phoneRepository.deleteById(theId);
    }
}