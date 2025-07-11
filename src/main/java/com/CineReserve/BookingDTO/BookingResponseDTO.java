package com.CineReserve.BookingDTO;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponseDTO implements Serializable{
    private String bookingId;
    private String movieName;
    private String theatreName;
    private String seatNumber;
    private String userEmail;
    private String status;
}
