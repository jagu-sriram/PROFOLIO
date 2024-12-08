package com.klef.jfsd.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.sdp.model.MileStoneProgress;
import com.klef.jfsd.sdp.model.MileStone;


@Repository
public interface MileStoneProgressRepository extends JpaRepository<MileStoneProgress, Integer>
{
	@Query("from MileStoneProgress mp where mp.milestone=?1")
	public MileStoneProgress findByMilestone(MileStone milestone);
}
