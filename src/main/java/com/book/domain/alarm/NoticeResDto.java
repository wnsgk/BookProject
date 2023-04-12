package com.book.domain.alarm;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeResDto {

    private Long id;

    private String content;

    private boolean checked;

    private LocalDateTime createdAt;
}
