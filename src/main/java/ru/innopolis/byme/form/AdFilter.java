package ru.innopolis.byme.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdFilter {
    int categoryId;
    int cityId;
}
