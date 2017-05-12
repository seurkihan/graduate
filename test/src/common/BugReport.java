package common;

import java.util.ArrayList;

public class BugReport {
	
	int bugID;
	String reporter;
	String product;
	String component;
	String productVer;
	String hardware;
	String assignee;
	String openDate;
	String modifiedDate;
	String status;
	String priority;
	public String getHardware() {
		return hardware;
	}
	public void setHardware(String hardware) {
		this.hardware = hardware;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	String sever;
	String summury;
	String description;
	ArrayList<Comment> commentList = new ArrayList<Comment>();
	ArrayList<Commit> commitList = new ArrayList<Commit>();
	public int getBugID() {
		return bugID;
	}
	public void setBugID(int bugID) {
		this.bugID = bugID;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getProductVer() {
		return productVer;
	}
	public void setProductVer(String productVer) {
		this.productVer = productVer;
	}
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSever() {
		return sever;
	}
	public void setSever(String sever) {
		this.sever = sever;
	}
	public String getSummury() {
		return summury;
	}
	public void setSummury(String summury) {
		this.summury = ForXML.forXML(summury);
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String descrliption) {
		this.description = ForXML.forXML(descrliption);
	}
	public ArrayList<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(ArrayList<Comment> commentList) {
		this.commentList = commentList;
	}
	public ArrayList<Commit> getCommitList() {
		return commitList;
	}
	public void setCommitList(ArrayList<Commit> commitList) {
		this.commitList = commitList;
	}
	
	public void addComment (Comment comment){
		this.commentList.add(comment);
	}
	public void addCommit (Commit commit){
		this.commitList.add(commit);
	}
	@Override
	public String toString() {
		return "BugReport [bugID=" + bugID + ", product=" + product + ", component=" + component + ", productVer="
				+ productVer + ", reporter=" + reporter + ", openDate=" + openDate + ", modifiedDate=" + modifiedDate
				+ ", status=" + status + ", sever=" + sever + ", summury=" + summury + ", description=" + description
				+ ", commentList=" + commentList.toString() + ", commitList=" + commitList.toString() + "]";
	}
	
	
	

}
