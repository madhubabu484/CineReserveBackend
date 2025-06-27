package com.CineReserve.CustomGenaratorid;

import java.util.Random;
import java.util.UUID;

public class Customgenaratorid {
	
	
	    public static String generateNextId() {
	        return "YOURBOOKINGID-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
	    }
	

	    }
	



