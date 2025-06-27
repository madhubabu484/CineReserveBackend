package com.CineReserve.HibenateGenaratorId;

import java.util.UUID;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class HibernateGenaratorid implements IdentifierGenerator  {

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) {

		String id = "BOOK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        System.out.println("Generated Booking ID: " + id); // ✅ log here
        return id;
		
	}

}
