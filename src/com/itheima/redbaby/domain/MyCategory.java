package com.itheima.redbaby.domain;

public class MyCategory {

//	 "id": 1010201,
//     "isleafnode": true,
//     "name": "防辐射服",
//     "parent_id": 10102,
//     "pic": "",
//     "tag": ""
	private String id;
	private String isleafnode;
	private String name;
	private String parent_id;
	private String pic;
	private String tag;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIsleafnode() {
		return isleafnode;
	}
	public void setIsleafnode(String isleafnode) {
		this.isleafnode = isleafnode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "MyCategory [id=" + id + ", isleafnode=" + isleafnode
				+ ", name=" + name + ", parent_id=" + parent_id + ", pic="
				+ pic + ", tag=" + tag + "]";
	}
	
}
