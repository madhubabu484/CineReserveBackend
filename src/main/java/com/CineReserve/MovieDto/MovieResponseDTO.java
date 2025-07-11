
package com.CineReserve.MovieDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieResponseDTO {
	private Long id;
	private String name;
	private String genre;
	private String heroName;
	private String heroineName;
}