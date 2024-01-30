package com.example.poketra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenteParGenre {
    Integer sum;
    Integer genre;
    Integer id_poketra;
    Poketra poketra;
}
