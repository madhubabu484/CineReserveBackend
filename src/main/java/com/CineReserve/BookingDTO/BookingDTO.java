package com.CineReserve.BookingDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Movie ID is required")
    private Long movieId;

    @NotNull(message = "Theatre ID is required")
    private Long theatreId;

    @NotBlank(message = "Seat number is required")
    private String seatNumber;
}
