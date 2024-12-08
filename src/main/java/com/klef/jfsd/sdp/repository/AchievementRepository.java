package com.klef.jfsd.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.sdp.model.Achievement;
import java.util.List;
import com.klef.jfsd.sdp.model.Portfolio;


@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Integer>
{
	@Query("from Achievement a where a.portfolio=?1 and a.title=?2")
	public Achievement findByPortfolioAndTitle(Portfolio portfolio, String title);
	
	public List<Achievement> findByPortfolio(Portfolio portfolio);

}
