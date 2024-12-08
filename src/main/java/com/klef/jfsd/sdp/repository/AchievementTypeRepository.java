package com.klef.jfsd.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.sdp.model.AchievementType;


@Repository
public interface AchievementTypeRepository extends JpaRepository<AchievementType, Long>
{
	@Query("from AchievementType at where at.id=?1")
	public AchievementType getAchievementTypeById(Long id);

}
