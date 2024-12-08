package com.klef.jfsd.sdp.model;

import java.util.ArrayList;
import java.util.List;

import com.klef.jfsd.sdp.model.MileStone.Status;

public class PredefinedMilestones 
{
	public static List<MileStone> getStaticMilestones(Project project) {
        List<MileStone> milestones = new ArrayList<>();
        String[] titles = {"Research", "Prototype", "Build", "Test", "Review"};
        int order = 1;

        for (String title : titles) {
            MileStone milestone = new MileStone();
            milestone.setTitle(title);
            milestone.setDescription(title + " milestone for the project.");
            milestone.setStatus(Status.NOT_STARTED);
            milestone.setOrderIndex(order++);
            milestone.setProgressPercentage(0);
            milestone.setReviewed(true);
            milestone.setProject(project);
            milestones.add(milestone);
        }
        return milestones;
    }

}
