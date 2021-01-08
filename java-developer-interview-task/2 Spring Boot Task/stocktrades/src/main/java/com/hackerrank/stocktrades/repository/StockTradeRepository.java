package com.hackerrank.stocktrades.repository;

import com.hackerrank.stocktrades.model.StockTrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockTradeRepository extends JpaRepository<StockTrade, Integer> {

    @Query(value = "select st from StockTrade st " +
            "where (:type is null or st.type=:type) " +
            "and (:userId is null or st.userId=:userId) " +
            "order by st.id")
    List<StockTrade> getTradesSortedByIds(@Param("type") String type,
                                          @Param("userId") Integer userId);

}
