package com.restaurant.review_platform.repository;

import com.restaurant.review_platform.domain.entity.Restaurant;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends ElasticsearchRepository<Restaurant, String> {
    // TODO: Custom queries
}
