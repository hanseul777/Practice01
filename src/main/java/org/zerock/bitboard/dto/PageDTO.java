package org.zerock.bitboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {

    @Builder.Default //builder.build할 때 default값을 지정한 값으로 준다.
    private int page = 1;

    @Builder.Default
    private int size = 10;

    //skip하는 양은 페이지마다 계속 변경되기 때문에 메서드로 표현
    //1페이지 - 0개스킵
    //2페이지 - 10개스킵
    //3페이지 - 20개스킵
    public int getSkip(){ //#{skip}으로 mapper에서 호출
        return (this.page-1) * size;
    }
}
