package org.zerock.bitboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttachDTO {
    //DB연결에 사용하기 위해 첨부파일용 DTO를 생성
    //BoardDTO를 참고해서 생성함

    private Integer bno;
    private String fname, savename;
    private boolean imgyn;
}
