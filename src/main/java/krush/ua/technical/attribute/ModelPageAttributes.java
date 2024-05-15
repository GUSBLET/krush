package krush.ua.technical.attribute;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ModelPageAttributes<T> {

    String title;
    String content;
    T entity;
}
