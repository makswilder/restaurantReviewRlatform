package com.restaurant.review_platform.mapper;

import com.restaurant.review_platform.domain.dto.PhotoDto;
import com.restaurant.review_platform.domain.entity.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhotoMapper {
    PhotoDto toDto(Photo photo);
}
