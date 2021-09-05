package org.zerock.bitboard.dao;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.zerock.bitboard.dto.AttachDTO;
import org.zerock.bitboard.dto.BoardDTO;
import org.zerock.bitboard.dto.PageDTO;

import java.util.List;

@Log4j2
public enum BoardDAO {
    INSTANCE;
    //SqlSession이 connection의 기능을해준다.
    //매개변수가 너무 많으면 dto로 한 번에 던지기
    private static final String PREFIX ="org.zerock.bitboard.dao.BoardMapper";

    //register도 insert로 사용하기 위해서 void -> Interger로 변경해줌. (ResigerController의 bno로 불리기 위해서
    public Integer insert(BoardDTO boardDTO) throws RuntimeException {
        //SqlSession이 connection의 기능을해준다.
        Integer bno = null;// 예외처리 안에서 return은 가능하면 한 번만 하도록 위에 변수를 빼줘서 맨 마지막에 리턴을 넣어준다.
        try (SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)){
            session.insert(PREFIX+".insert",boardDTO);
            bno = boardDTO.getBno();

            //첨부파일 관련 추가
            List<AttachDTO> attachDTOList = boardDTO.getAttachDTOList();
            //attachDTOList에 첨부파일이 없으면 null
            // 첨부파일이 있으면 for진행
            if(attachDTOList != null && attachDTOList.size() > 0){
                for (AttachDTO attachDTO : attachDTOList){
                    attachDTO.setBno(bno);//bno먼저 추가하고 시작
                    session.insert(PREFIX + ".insertAttach", attachDTO);
                }
            }
            session.commit();

        }catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return bno;
    }

    //dto안의 변수들을 출력해서 한 줄을 표현 -> selectOne
    public BoardDTO select(Integer bno) throws RuntimeException {
        BoardDTO dto = null;
        try (SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)){
            dto = session.selectOne(PREFIX+".select",bno); // 한 개의 항목만 가져오면 selectOne
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return dto;
    }

    //데이터를 정렬하고 페이지를 구분하기 시작 : 1페이지에 몇 개의 bno를 skip하고 몇 개를 보여줄건지(size)
    //skip과 size에 대한 정의가 필요함 -> PageDTO생성
    //위에서 표현한 dto들의 묶음(한 줄)을 list로 만들어서 여러 줄을 표현 -> selectList
    //그냥 제한없이 표현하는게 아니라 페이징을 해서 표현 -> PageDTO를 매개변수로 설정
    public List<BoardDTO> list(PageDTO pageDTO) throws RuntimeException {
        List<BoardDTO> list = null;
        try (SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)){
            list = session.selectList(PREFIX+".list",pageDTO);
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }

    public void delete(Integer bno) throws RuntimeException{
        try (SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)){
            session.delete(PREFIX+".delete",bno); // 한 개의 항목만 가져오면 selectOne
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public void update(BoardDTO dto)throws RuntimeException {
        try (SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession(true)){
            session.update(PREFIX+".update",dto); // 한 개의 항목만 가져오면 selectOne
        }catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

}
