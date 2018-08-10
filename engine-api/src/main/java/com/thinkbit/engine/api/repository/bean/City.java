package com.thinkbit.engine.api.repository.bean;


import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class City {

    private Long id;

    private String name;

    private String state;
}
