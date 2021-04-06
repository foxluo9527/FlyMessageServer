package com.po;

public class UserPageQuery {
	private int pageSize;
	private int pageIndex;
	private int u_id;
	private int object_u_id;
	private String queryString;
	private String likeString;
	/**
	 * @return the object_u_id
	 */
	public int getObject_u_id() {
		return object_u_id;
	}
	/**
	 * @param object_u_id the object_u_id to set
	 */
	public void setObject_u_id(int object_u_id) {
		this.object_u_id = object_u_id;
	}
	/**
	 * @return the queryString
	 */
	public String getQueryString() {
		return queryString;
	}
	/**
	 * @param queryString the queryString to set
	 */
	public void setQueryString(String queryString) {
		if (queryString!=null&&!queryString.isEmpty()) {
			this.queryString = queryString;
			this.likeString="%"+queryString+"%";
		}else {
			this.queryString = queryString;
			this.likeString="% %";
		}
	}
	
	/**
	 * @return the likeString
	 */
	public String getLikeString() {
		return likeString;
	}
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		this.pageIndex = (pageIndex-1)*pageSize;
	}
	/**
	 * @return the pageIndex
	 */
	public int getPageIndex() {
		return pageIndex;
	}
	/**
	 * @param pageIndex the pageIndex to set
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	/**
	 * @return the u_id
	 */
	public int getU_id() {
		return u_id;
	}
	/**
	 * @param u_id the u_id to set
	 */
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	
}
