package com.rms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rms.dao.AddressDao;
import com.rms.dto.AddressDto;
import com.rms.entity.Address;
import com.rms.exception.BusinessLogicException;
import com.rms.exception.DataBaseException;
import com.rms.service.AddressService;
import com.rms.util.AddressUtil;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao addressDao;

	static final String ADDR_NOT_FOUND = "No records Found for Address";

	@Override
	public String addAddress(AddressDto addressDto) {
		String result = null;
		try {
			Address address = AddressUtil.toEntity(addressDto);
			boolean stat = addressDao.addAddress(address);
			if (stat) {
				result = "Address Added Successfully!";
			}
			return result;
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public List<AddressDto> getAddressByPhoneNumber(Long phoneNumber) {
		try {
			List<Address> addressEntity = addressDao.getAddressByPhoneNumber(phoneNumber);
			if (addressEntity != null) {
				List<AddressDto> addressDto = new ArrayList<>();
				addressEntity.stream().forEach(entity -> addressDto.add(AddressUtil.toDto(entity)));
				return addressDto;
			} else {
				throw new BusinessLogicException(ADDR_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}
	}

	@Override
	public Long getAddressByCustomerId(Long customerId) {

		try {
			Long result = null;
			Address addressEntity = addressDao.getAddressByCustomerId(customerId);
			if (addressEntity != null) {
				result = addressEntity.getId();

				return result;
			} else {
				throw new BusinessLogicException(ADDR_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}

	}

	@Override
	public AddressDto getAddressById(Long id) {
		try {
			Address address = addressDao.getAddressById(id);
			if (address != null) {
				return AddressUtil.toDto(address);
			} else {
				throw new BusinessLogicException(ADDR_NOT_FOUND);
			}
		} catch (DataBaseException e) {
			throw new BusinessLogicException(e.getMessage());
		}

	}

}
