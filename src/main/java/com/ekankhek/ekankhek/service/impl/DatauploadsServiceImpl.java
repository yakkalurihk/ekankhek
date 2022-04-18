package com.ekankhek.ekankhek.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekankhek.ekankhek.dao.DatauploadsREpository;
import com.ekankhek.ekankhek.domain.Datauploads;
import com.ekankhek.ekankhek.domain.User;
import com.ekankhek.ekankhek.service.DatauploadsService;

@Service("datauploadsService")
public class DatauploadsServiceImpl implements DatauploadsService{

	@Autowired
	DatauploadsREpository dataRepo;
	
	@Override
	public List<Datauploads> findByUsername(User user) {
		
		return dataRepo.findByUsername(user);
	}

	@Override
	public Datauploads save(Datauploads datauploads) {
		// TODO Auto-generated method stub
		return dataRepo.save(datauploads);
	}

	@Override
	public boolean delete(Datauploads datauploads) {
		// TODO Auto-generated method stub
		try {
			dataRepo.delete(datauploads);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public Datauploads findById(Long id) {
		// TODO Auto-generated method stub
		return dataRepo.getById(id);
	}

}
