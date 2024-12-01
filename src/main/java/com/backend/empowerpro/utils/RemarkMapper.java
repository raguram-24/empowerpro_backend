package com.backend.empowerpro.utils;

import com.backend.empowerpro.dto.remark.RemarkCreationDto;
import com.backend.empowerpro.dto.remark.RemarkDto;
import com.backend.empowerpro.entity.Remark;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RemarkMapper {
    RemarkDto toRemarkDto(Remark remark);

    Remark toRemark(RemarkCreationDto remarkCreationDto);
}
