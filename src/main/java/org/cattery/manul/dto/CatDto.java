package org.cattery.manul.dto;

import lombok.Builder;
import lombok.Data;
import org.cattery.manul.enums.CatColor;

import java.time.Instant;
import java.util.UUID;


@Data
@Builder
public class CatDto {

    private UUID id;
    private String name;
    private UUID mother;
    private UUID father;
    private Instant sold;
    private CatColor color;

}
