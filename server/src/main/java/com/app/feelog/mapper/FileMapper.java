package com.app.feelog.mapper;

import com.app.feelog.domain.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileMapper {

    public void insert(FileVO fileVO);

    void deactivateFile(Long fileId);

    void softDeleteFile(@Param("fileId") Long fileId);

    void softDeleteByFileName(@Param("fileName") String fileName);

}
