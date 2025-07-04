package com.CineReserve.BookingDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponseDTO {
    private String bookingId;
    private String movieName;
    private String theatreName;
    private String seatNumber;
    private String userEmail;
    private String status;
}
