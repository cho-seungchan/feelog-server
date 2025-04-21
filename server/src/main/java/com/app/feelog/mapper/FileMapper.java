package com.app.feelog.mapper;

import com.app.feelog.domain.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {

    public void insert(FileVO fileVO);

    void deactivateFile(Long fileId);

}
