package com.congdinh.dto.category;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;

    @NotNull(message = "Name is required")
    @Length(min = 5, max = 255, message = "Name must be between 5 and 255 characters")
    private String name;

    @Length(max = 1000, message = "Description must be less than 1000 characters")
    private String description;
}
