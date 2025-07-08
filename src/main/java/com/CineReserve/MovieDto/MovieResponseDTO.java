package com.CineReserve.MovieDto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieResponseDTO {
	private Long id;
    private String name;
    private String genre;
    private String heroName;
    private String heroineName;
}

