package org.zerock.bitboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class BoardDTO {

    private Integer bno;
    private String title,content,writer;
    private int viewcount;
    private Timestamp regdate,updatedate;
}
