package com.klef.jfsd.sdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.sdp.model.PortfolioProject;
import java.util.List;
import com.klef.jfsd.sdp.model.Portfolio;
import com.klef.jfsd.sdp.model.Project;


@Repository
public interface PortfolioProjectRepository extends JpaRepository<PortfolioProject, Integer>
{
	@Query("from PortfolioProject pp where pp.portfolio=?1 and pp.project=?2")
	public PortfolioProject findByPortfolioAndProject(Portfolio portfolio, Project project);

	public List<PortfolioProject> findByPortfolio(Portfolio portfolio);
}
