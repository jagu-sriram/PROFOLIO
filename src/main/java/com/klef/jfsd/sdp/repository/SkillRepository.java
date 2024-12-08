package com.klef.jfsd.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.sdp.model.Skill;
import java.util.List;
import com.klef.jfsd.sdp.model.Portfolio;
import com.klef.jfsd.sdp.model.SkillType;


@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer>
{
	@Query("from Skill s where s.portfolio=?1 and s.skillType=?2")
	public Skill findByPortfolioAndSkillType(Portfolio portfolio, SkillType skillType);
	
	public List<Skill> findByPortfolio(Portfolio portfolio);

}
