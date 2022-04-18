package com.ekankhek.ekankhek.service;

import java.util.List;

import com.ekankhek.ekankhek.domain.Datauploads;
import com.ekankhek.ekankhek.domain.User;

public interface DatauploadsService {

	Datauploads save(Datauploads datauploads);
	boolean delete(Datauploads datauploads);
	List<Datauploads> findByUsername(User user);
	Datauploads findById(Long id);
}
