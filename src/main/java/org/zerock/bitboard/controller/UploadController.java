package org.zerock.bitboard.controller;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.Collection;

@Log4j2
@WebServlet(name = "upload", value = "/upload")
@MultipartConfig(fileSizeThreshold = 1024*1024) //첨부파일을 만들기 위해서 사용. 파일사이즈에 제한을 준다.
public class UploadController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/upload.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uploadfolder = "/User/hanseul/upload"; //업로드할 폴더 선택
        byte[] buffer = new byte[1024*8]; //파일복사할 buffer생성
        Collection<Part>parts = request.getParts();

        parts.forEach(part -> {
            log.info(part);

            String type = part.getContentType();
            //type = null -> 우리가 사용하던 일반적인 파일형식
            //일반파일과 이미지파일의 업로드가 다르다.
            if(type == null){
                log.info("partName : " + part.getName());//일반파일을 처리할 때만 사용
                return; //일반파일이 아니면 그냥 내려간다.
            }
            String fileName = part.getSubmittedFileName();

            //파일명 지정.중복파일이 있을 수 있으므로 currentTimeMillis()를 이용해 시간과 같이 출력
            String uploadFileName = System.currentTimeMillis()+"_"+fileName;

            log.info(fileName);

            //원본파일저장
            try (InputStream in = part.getInputStream();
                 OutputStream fos = new FileOutputStream(uploadfolder+ File.separator+uploadFileName); //원본파일의 오리지널네임
                 //File.separator 파일경로
            ) {
                while (true){
                    int count = in.read(buffer);
                    if(count == -1){break;}
                    fos.write(buffer,0,count);
                }
            }catch(Exception e){

            }//원본파일 저장 끝

            //썸네일만들기
            //썸네일은 이미지파일에서만 생성 -> 이미지로 시작하는 파일들만 썸네일을 생성
            if(type.startsWith("image")){
                try{
                    Thumbnails.of(new File(uploadfolder+ File.separator+uploadFileName))//오리지널 파일
                            .size(100,100)//100픽셀짜리로 만들어 줌
                            .toFile(new File(uploadfolder+ File.separator+"s_"+uploadFileName));
                }catch (Exception e){}

            }
            log.info("---------------------------------"); // 파일하나씩 보기좋게 구분선 넣음
        });//for each END
    }
}
