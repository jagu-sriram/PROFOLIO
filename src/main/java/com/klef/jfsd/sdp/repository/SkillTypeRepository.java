package com.klef.jfsd.sdp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.sdp.model.SkillType;

@Repository
public interface SkillTypeRepository extends JpaRepository<SkillType, Long>
{
	@Query("from SkillType s where s.id=?1")
	public SkillType getSkillTypeById(Long id);
	
	
}
