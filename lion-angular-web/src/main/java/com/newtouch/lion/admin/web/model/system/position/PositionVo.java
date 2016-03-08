/**
 * 
 */
package com.newtouch.lion.admin.web.model.system.position;

/**
 * @author LiXiaoHao
 *
 */
public class PositionVo {

	/**记录ID*/
	private Long  id;
	/**
	 * @Fields nameEn：岗位英文名称
	 */
	private String nameEn;
	/**
	 * @Fields nameZh：岗位中文名称
	 */
	private String nameZh;
	/**
	 *@Fields description:岗位描述
	 */
	private String description;
	/**
	 * @Fidlds departmentId:所在部门id
	 * **/
	private Long departmentId;
	/**
	 * 
	 */
	public PositionVo() {
		super();
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the nameEn
	 */
	public String getNameEn() {
		return nameEn;
	}
	/**
	 * @param nameEn the nameEn to set
	 */
	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}
	/**
	 * @return the nameZh
	 */
	public String getNameZh() {
		return nameZh;
	}
	/**
	 * @param nameZh the nameZh to set
	 */
	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	/**
	 * @return the departmentId
	 */
	public Long getDepartmentId() {
		return departmentId;
	}
	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PositionVo [id=" + id + ", nameEn=" + nameEn + ", nameZh="
				+ nameZh + ", description=" + description + "]";
	}
	
	
}
